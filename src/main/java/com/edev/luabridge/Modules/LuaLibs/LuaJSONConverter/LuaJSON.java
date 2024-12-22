package com.edev.luabridge.Modules.LuaLibs.LuaJSONConverter;

import org.json.JSONObject;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.ZeroArgFunction;

public class LuaJSON extends ZeroArgFunction {
    private static LuaJSONClasse luaJSONClasse = new LuaJSONClasse();

    @Override
    public LuaValue call() {
        LuaTable LuaJSON = new LuaTable();
        LuaJSON.set("luaToJson", new LuaToJSON());

        return LuaJSON;
    }

    static class LuaToJSON extends OneArgFunction {

        @Override
        public LuaValue call(LuaValue luaValue) {
            if (luaValue.istable()) {
                LuaTable table = luaValue.checktable();
                JSONObject tableJson = luaJSONClasse.luaTableToJson(table);

                // Retorna o JSONObject como string para o script Lua
                return LuaValue.valueOf(tableJson.toString());
            }
            return LuaValue.NIL;
        }
    }

    static class JsonToLua extends OneArgFunction {

        @Override
        public LuaValue call(LuaValue jsonString) {
            if (jsonString.isstring()) {
                String json = jsonString.checkjstring();
                JSONObject jsonObject = new JSONObject(json);
                LuaTable luaTable = luaJSONClasse.jsonToLuaTable(jsonObject);

                return luaTable;
            }
            return LuaValue.NIL;
        }
    }
}
