package com.edev.luabridge.Modules.Configuration.LuaConfig;

import org.luaj.vm2.Globals;
import org.luaj.vm2.lib.jse.JsePlatform;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//Configuração global do Lua para o JVM.
@Configuration
public class LuaConfiguration {
    @Bean
    public Globals luaGlobais() {
        return JsePlatform.standardGlobals();
    }

}
