package com.edev.luabridge.Modules.LuaServices;

import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;



public interface LuaServices {
    //Retornos Públicos
    ResponseEntity<?> runScript(String script, List<Map<String, Object>> params);

}
