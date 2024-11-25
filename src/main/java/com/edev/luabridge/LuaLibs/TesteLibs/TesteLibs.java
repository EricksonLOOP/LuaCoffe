package com.edev.luabridge.LuaLibs.TesteLibs;

import com.edev.luabridge.LuaLibs.LuaDB.DataBaseManager;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.ThreeArgFunction;
import org.luaj.vm2.lib.TwoArgFunction;
import org.luaj.vm2.lib.ZeroArgFunction;

public class TesteLibs extends ZeroArgFunction {
    Globals globals;
    @Override
    public LuaValue call() {
        LuaTable library = new LuaTable();

        return LuaValue.valueOf("Retorno");
    }


}
