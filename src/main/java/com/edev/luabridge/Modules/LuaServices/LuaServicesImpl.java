package com.edev.luabridge.Modules.LuaServices;

import com.edev.luabridge.Modules.LuaLibs.Libs.Libs;
import com.edev.luabridge.Modules.LuaLibs.LuaDB.DataBaseManager;
import com.edev.luabridge.Modules.FunctionsServices.LuaActions;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaError;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class LuaServicesImpl implements LuaServices{
    final private Globals globals;

    @Autowired
    final private LuaActions luaActions;
    private DataBaseManager dataBaseManager = new DataBaseManager();
    public LuaServicesImpl(Globals globals, LuaActions luaActions) {
        this.globals = globals;
        this.luaActions = luaActions;
    }

    @Override
    public ResponseEntity<?> runScript(String script, List<Map<String, Object>> params) {
        try{


            String complete = luaActions.ReplaceWaitingValues(script, params);
            LuaTable luacoffe = new LuaTable();
            luacoffe.set("libs", new Libs().call());
            globals.set("luacoffe", luacoffe);
            LuaValue chunk = globals.load(complete);
            LuaValue response = chunk.call();
            return ResponseEntity.ok().body(response.toString());
        } catch (LuaError e) {
            // Handle Lua-specific exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro na execução do script Lua: " + e.getMessage());
        }catch(NullPointerException e){
            return ResponseEntity.badRequest().body("O arquivo não tem um script para ser executado");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
