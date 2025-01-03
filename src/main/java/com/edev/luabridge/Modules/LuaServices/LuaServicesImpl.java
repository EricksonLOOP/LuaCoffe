package com.edev.luabridge.Modules.LuaServices;

import com.edev.luabridge.Models.LuaCoffeLuaReturnModel.LuaReturn;
import com.edev.luabridge.Modules.LuaLibs.Libs.Libs;
import com.edev.luabridge.Modules.LuaLibs.LuaDB.DataBaseManager;
import com.edev.luabridge.Modules.FunctionsServices.LuaActions;
import org.luaj.vm2.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.nio.file.Paths;
import java.util.Map;

@Service
public class LuaServicesImpl implements LuaServices{
    private final Globals globals;
    private final LuaActions luaActions;
    private final DataBaseManager dataBaseManager = new DataBaseManager();
    @Value("${file.package.path}")
    String importsPath;
    @Autowired
    public LuaServicesImpl(Globals globals, LuaActions luaActions) {
        this.globals = globals;
        this.luaActions = luaActions;
    }

    @Override
    public LuaReturn runScriptApi(String script, Map<String, Object> params, String path) {
        try {
            String complete = luaActions.ReplaceWaitingValues(script, params, path);
            LuaTable luacoffe = new LuaTable();
            luacoffe.set("libs", new Libs().call());
            globals.set("luaCoffe", luacoffe);
            String currentDirectory = Paths.get("").toAbsolutePath().toString();

            globals.set("package.path", currentDirectory +importsPath);
            LuaValue chunk = globals.load(complete);
            LuaValue response = chunk.call();
            if (!response.istable()) {
                throw new LuaError("You should return a LuaResponse table.");
            }
            LuaTable table = response.checktable();
            if (table.get("code").isnil() || table.get("response").isnil(1)) {
                throw new LuaError("The LuaResponse table must contain at least two values.");
            }

            return new LuaReturn(Integer.parseInt(table.get("code").toString()), table.get("response"));

        } catch (LuaError e) {
            throw new LuaError("LuaError: " + e.getMessage());
        } catch (NullPointerException e) {
            throw new NullPointerException("NullPointerException: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error: " + e.getMessage());
        }
    }

    @Override
    public LuaReturn runScriptPages(String script, Map<String, Object> params, String path, String action) {
        try {
            // Substituir variáveis no script (se necessário)
            String complete = luaActions.ReplaceWaitingValues(script, params, path);

            // Criar o contexto global
            LuaTable luacoffe = new LuaTable();
            luacoffe.set("libs", new Libs().call());
            globals.set("luaCoffe", luacoffe);

            String currentDirectory = Paths.get("").toAbsolutePath().toString();
            globals.set("package.path", currentDirectory + importsPath);

            // Inicializar o estado global no contexto Lua
            LuaTable state = new LuaTable();
            globals.set("luaCoffe", luacoffe);
            globals.set("state", state);


            LuaValue chunk = globals.load(complete);
            chunk.call();


            LuaTable eventsTable = globals.get("luaCoffe").get("libs").get("events").get("eventsList").checktable();


            LuaValue event = eventsTable.get(action);

            if (!event.isnil() && event.isfunction()) {
                LuaFunction eventFunction = event.checkfunction();
                eventFunction.call();
            }


            LuaValue result = chunk.call();
            LuaTable responseTable = result.checktable();

            if (responseTable.get("code").isnil() || responseTable.get("response").isnil(1)) {
                throw new LuaError("A tabela LuaResponse deve conter pelo menos dois valores.");
            }

            return new LuaReturn(Integer.parseInt(responseTable.get("code").toString()), responseTable.get("response"));

        } catch (LuaError e) {
            throw new LuaError("Erro no Lua: " + e.getMessage());
        } catch (NullPointerException e) {
            throw new NullPointerException("Erro de ponteiro nulo: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado: " + e.getMessage());
        }
    }

}
