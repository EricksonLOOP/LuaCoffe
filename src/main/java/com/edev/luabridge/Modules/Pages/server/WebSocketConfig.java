package com.edev.luabridge.Modules.Pages.server;

import com.edev.luabridge.Modules.File.FileServices;
import com.edev.luabridge.Modules.LuaServices.LuaServices;
import com.edev.luabridge.Modules.Pages.events.EventsLib;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Autowired
    private final LuaServices luaServices;
    @Autowired
    private final FileServices fileServices;
    @Autowired
    private final EventsLib eventsLib;
    public WebSocketConfig(LuaServices luaServices, FileServices fileServices, EventsLib eventsLib) {
        this.luaServices = luaServices;
        this.fileServices = fileServices;
        this.eventsLib = eventsLib;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // Registra um WebSocketHandler para uma URL específica
        registry.addHandler(new WebSocketHandler(eventsLib, fileServices, luaServices), "/ws").setAllowedOrigins("*");
    }
}
