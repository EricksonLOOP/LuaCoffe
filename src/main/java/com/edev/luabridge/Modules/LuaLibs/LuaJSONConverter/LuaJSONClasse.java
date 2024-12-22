package com.edev.luabridge.Modules.LuaLibs.LuaJSONConverter;

import org.json.JSONObject;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;

public class LuaJSONClasse {
    public static JSONObject luaTableToJson(LuaTable luaTable){
        JSONObject json = new JSONObject();
        LuaValue key = LuaValue.NIL;
        while (true) {
            Varargs next = luaTable.next(key);
            if ((key = next.arg1()).isnil()) break;
            LuaValue value = next.arg(2);

            if (value.istable()) {
                json.put(key.tojstring(), luaTableToJson(value.checktable()));
            } else {
                json.put(key.tojstring(), value.tojstring());
            }
        }
        return json;
    }
    public static LuaTable jsonToLuaTable(JSONObject jsonObject) {
        LuaTable luaTable = new LuaTable();

        for (String key : jsonObject.keySet()) {
            Object value = jsonObject.get(key);

            if (value instanceof JSONObject) {
                luaTable.set(key, jsonToLuaTable((JSONObject) value));
            } else {
                luaTable.set(key, LuaValue.valueOf(value.toString()));
            }
        }
        return luaTable;
    }
}
