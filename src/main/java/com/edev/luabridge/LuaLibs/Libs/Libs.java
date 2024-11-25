package com.edev.luabridge.LuaLibs.Libs;

import com.edev.luabridge.LuaLibs.LuaDB.LuaDB;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.ZeroArgFunction;

public class Libs extends ZeroArgFunction {
    @Override
    public LuaValue call() {
        LuaTable libs = new LuaTable();
        libs.set("DataBase", new LuaDB().call());
        return libs;
    }
}
