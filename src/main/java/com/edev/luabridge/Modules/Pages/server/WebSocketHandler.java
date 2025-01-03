package com.edev.luabridge.Modules.Pages.server;

import com.edev.luabridge.Models.LuaCoffeLuaReturnModel.LuaReturn;
import com.edev.luabridge.Modules.File.FileServices;
import com.edev.luabridge.Modules.File.FileServicesImpl;
import com.edev.luabridge.Modules.LuaServices.LuaServices;
import com.edev.luabridge.Modules.LuaServices.LuaServicesImpl;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import jakarta.annotation.PostConstruct;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
   private final FileServices fileServices;
    @Autowired
    private final LuaServices luaServices;
    private String validOrigin = "http://localhost:8080";

    public WebSocketHandler(FileServices fileServices, LuaServices luaServices) {
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
                String requestUri = session.getUri().toString();
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
        String[] paths = endpoint.split("/");
        String luascript = endpoint.substring(endpoint.lastIndexOf('/') + 1);
        Optional<File> fileOpt = Optional.ofNullable(fileServices.encontrarArquivos(luascript, "get"));
        if (fileOpt.isEmpty()) {
            return null;
        }

        File scriptFile = fileOpt.get();
        String readFile = fileServices.readFile(scriptFile);
        if (readFile.isEmpty()) {
            return null;
        }

        LuaReturn luaReturn = luaServices.runScript(readFile, Collections.emptyMap(), endpoint);
        return getReturnValue(luaReturn).toString();
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
