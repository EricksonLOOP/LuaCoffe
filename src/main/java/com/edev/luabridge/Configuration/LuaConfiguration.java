package com.edev.luabridge.Configuration;

import com.edev.luabridge.LuaLibs.Libs.Libs;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.lib.jse.JsePlatform;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LuaConfiguration {
    @Bean
    public Globals luaGlobais() {
        return JsePlatform.standardGlobals();
    }

}
