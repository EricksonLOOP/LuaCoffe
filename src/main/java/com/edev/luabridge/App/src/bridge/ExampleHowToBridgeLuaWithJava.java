package com.edev.luabridge.App.src.bridge;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.ZeroArgFunction;

public class ExampleHowToBridgeLuaWithJava {
    public static class ExampleModule extends ZeroArgFunction{

        @Override
        public LuaValue call() {
            return null;
        }
    }
}
