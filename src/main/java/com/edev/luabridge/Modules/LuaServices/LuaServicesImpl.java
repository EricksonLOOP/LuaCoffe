package com.edev.luabridge.Modules.LuaServices;

import com.edev.luabridge.Models.LuaCoffeLuaReturnModel.LuaReturn;
import com.edev.luabridge.Modules.LuaLibs.Libs.Libs;
import com.edev.luabridge.Modules.LuaLibs.LuaDB.DataBaseManager;
import com.edev.luabridge.Modules.FunctionsServices.LuaActions;
import org.luaj.vm2.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;
import java.util.List;
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
    public LuaReturn runScript(String script, List<Map<String, Object>> params, String path) {
        try {
            String complete = luaActions.ReplaceWaitingValues(script, params, path);
            LuaTable luacoffe = new LuaTable();
            luacoffe.set("libs", new Libs().call());
            globals.set("luacoffe", luacoffe);
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

}
