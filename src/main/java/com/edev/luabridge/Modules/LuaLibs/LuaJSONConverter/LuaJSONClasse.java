package com.edev.luabridge.Modules.LuaLibs.LuaJSONConverter;

import org.json.JSONArray;
import org.json.JSONObject;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;

public class LuaJSONClasse {
    public static JSONObject luaTableToJson(LuaTable luaTable) {
        JSONObject json = new JSONObject();
        LuaValue key = LuaValue.NIL;

        while (true) {
            Varargs next = luaTable.next(key);
            if ((key = next.arg1()).isnil()) break;

            LuaValue value = next.arg(2);
            if (value.istable()) {
                LuaTable tableValue = value.checktable();
                if (isArray(tableValue)) {
                    JSONArray jsonArray = new JSONArray();
                    int i = 1;
                    while (true) {
                        LuaValue arrayValue = tableValue.get(i);
                        if (arrayValue.isnil()) break;

                        if (arrayValue.istable()) {
                            JSONObject nestedJson = luaTableToJson(arrayValue.checktable());
                            jsonArray.put(nestedJson);
                        } else {

                            jsonArray.put(arrayValue.tojstring());
                        }
                        i++;
                    }
                    json.put(key.tojstring(), jsonArray);
                } else {
                    JSONObject nestedJson = luaTableToJson(tableValue);
                    json.put(key.tojstring(), nestedJson);
                }
            } else {
                json.put(key.tojstring(), value.tojstring());
            }
        }

        return json;
    }

    private static boolean isArray(LuaTable table) {
        LuaValue firstKey = table.next(LuaValue.NIL).arg1();
        return firstKey.isint() && firstKey.checkint() == 1;
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
