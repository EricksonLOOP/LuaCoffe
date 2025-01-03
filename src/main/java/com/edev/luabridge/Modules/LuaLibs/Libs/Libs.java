package com.edev.luabridge.Modules.LuaLibs.Libs;

import com.edev.luabridge.Modules.LuaLibs.LuaDB.LuaDB;
import com.edev.luabridge.Modules.LuaLibs.LuaHttp.LuaOkHttp;
import com.edev.luabridge.Modules.LuaLibs.LuaJSONConverter.LuaJSON;
import com.edev.luabridge.Modules.Pages.components.ComponentsLib;
import com.edev.luabridge.Modules.Pages.events.EventsLib;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.ZeroArgFunction;
import org.springframework.stereotype.Component;

// Aqui fica armazenado as bibliotecas dentro do luaCoffe.libs
// Caso criar uma lib nova, crie um módlo novo, desenvolva ele, e adicione ele no luaCoffe.libs
@Component
public class Libs extends ZeroArgFunction {
    @Override
    public LuaValue call() {
        LuaTable libs = new LuaTable();
        libs.set("dataBase", new LuaDB().call());
        libs.set("luaJson", new LuaJSON().call());
        libs.set("luaOkHttp", new LuaOkHttp().call());
        libs.set("pages", new ComponentsLib().call());
        libs.set("events", new EventsLib().call());
        return libs;
    }
}
