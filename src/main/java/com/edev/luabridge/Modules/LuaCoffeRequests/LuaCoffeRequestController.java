package com.edev.luabridge.Modules.LuaCoffeRequests;

import com.edev.luabridge.Models.LuaCoffeLuaReturnModel.LuaReturn;
import com.edev.luabridge.Modules.LuaServices.LuaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Map;

@RestController
public class LuaCoffeRequestController {
    @Autowired
    private final LuaServices luaServices;

    public LuaCoffeRequestController(LuaServices luaServices) {
        this.luaServices = luaServices;
    }

    @GetMapping("/{endpoint}/{variables}")
    public ResponseEntity<?> LuaCoffeGetMethod(
            @PathVariable("endpoint") String endpoint,
            @PathVariable("variables")List<Map<String, Object>> params){
        String[] paths = endpoint.split("/");
        String luascript = paths[paths.length-1];
        LuaReturn luaReturn = luaServices.runScript(luascript, params);
        return ResponseEntity.status(HttpStatusCode.valueOf())
    }
}
