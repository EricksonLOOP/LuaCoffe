package com.edev.luabridge.Modules.Pages.server;

import com.edev.luabridge.Models.LuaCoffeLuaReturnModel.LuaReturn;
import com.edev.luabridge.Modules.File.FileServices;
import com.edev.luabridge.Modules.LuaServices.LuaServices;
import com.edev.luabridge.Modules.Pages.events.EventsLib;
import jakarta.annotation.PostConstruct;
import org.luaj.vm2.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.http.HttpHeaders;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class WebSocketHandler extends TextWebSocketHandler {
    @Autowired
    private final EventsLib eventsLib;
    @Autowired
   private final FileServices fileServices;
    @Autowired
    private final LuaServices luaServices;
    private String validOrigin = "http://localhost:8080";

    public WebSocketHandler(EventsLib eventsLib, FileServices fileServices, LuaServices luaServices) {
        this.eventsLib = eventsLib;
        this.fileServices = fileServices;
        this.luaServices = luaServices;
    }


    @PostConstruct
    public void init() {
        System.out.println("Valid Origin: " + validOrigin);  // Verifique o valor aqui
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        System.out.println("Mensagem recebida: " + message.getPayload());

        try {
            HttpHeaders handshakeHeaders = session.getHandshakeHeaders();


            if (verificarHost(session)) {
                String page = abrirPagina(message.getPayload());
                if ( page != null){
                    session.sendMessage(new TextMessage(page));
                }else{
                    session.sendMessage(new TextMessage("null"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Boolean verificarHost(WebSocketSession session){
        return session.getHandshakeHeaders().getOrigin().equals(validOrigin);
    }

    private String abrirPagina(String message) throws IOException {
        String[] messageIdx = message.split(",");
        String endpoint = messageIdx[0];
        String action = "";
       if (messageIdx.length>1){
           action = messageIdx[1];
       }

        Optional<File> fileOpt = Optional.ofNullable(fileServices.encontrarArquivos(endpoint.substring(endpoint.lastIndexOf('/') + 1), "get"));
        if (fileOpt.isEmpty()) {
            return null;
        }

        File scriptFile = fileOpt.get();
        String readFile = fileServices.readFile(scriptFile);
        if (readFile.isEmpty()) {
            return null;
        }

       LuaReturn luaReturn = luaServices.runScriptPages(readFile, Collections.emptyMap(), endpoint, action);



        return getReturnValue(luaReturn).toString();
    }

    private LuaReturn handleEvent(String action, LuaReturn firstLuareturn) {
        if (!action.equals("") && eventsLib.getEventsTable().get(action).checkfunction().isfunction()){
            LuaFunction actionFunction = eventsLib.getEventsTable().get(action).checkfunction();
            actionFunction.call();
           return firstLuareturn;
        }else{
            return firstLuareturn;
        }
    }

    public Object getReturnValue(LuaReturn luaReturn) {
        LuaValue returnObj = luaReturn.getReturnObj();


        if (returnObj.isstring()) {
            return returnObj.tojstring();
        } else if (returnObj.isnumber()) {
            return returnObj.toint();
        } else if (returnObj.istable()) {
            LuaTable luaTable = returnObj.checktable();
            return luaTableToMap(luaTable);
        } else if (returnObj.isboolean()) {
            return returnObj.toboolean();
        } else if (returnObj.isnil()) {
            return null;
        } else {
            return returnObj.tojstring();
        }
    }

    public Map<String, Object> luaTableToMap(LuaTable luaTable) {
        Map<String, Object> map = new HashMap<>();
        LuaValue k = LuaValue.NIL;
        while (true) {
            Varargs n = luaTable.next(k);
            if ((k = n.arg1()).isnil())
                break;
            LuaValue v = n.arg(2);

            if (v.isstring()) {
                map.put(k.tojstring(), v.tojstring());
            } else if (v.isnumber()) {
                map.put(k.tojstring(), v.toint());
            } else if (v.isboolean()) {
                map.put(k.tojstring(), v.toboolean());
            } else {
                map.put(k.tojstring(), v.tojstring());
            }
        }
        return map;
    }
}
