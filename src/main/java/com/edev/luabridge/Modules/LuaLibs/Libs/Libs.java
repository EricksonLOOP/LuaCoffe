package com.edev.luabridge.Modules.LuaLibs.Libs;

import com.edev.luabridge.Modules.LuaLibs.LuaDB.LuaDB;
import com.edev.luabridge.Modules.LuaLibs.LuaHttp.LuaOkHttp;
import com.edev.luabridge.Modules.LuaLibs.LuaJSONConverter.LuaJSON;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.ZeroArgFunction;

public class Libs extends ZeroArgFunction {
    @Override
    public LuaValue call() {
        LuaTable libs = new LuaTable();
        libs.set("DataBase", new LuaDB().call());
        libs.set("LuaJson", new LuaJSON().call());
        libs.set("LuaOkHttp", new LuaOkHttp().call());
        return libs;
    }
}
