package com.edev.luabridge.Modules.LuaServices;

import com.edev.luabridge.Models.LuaCoffeLuaReturnModel.LuaReturn;

import java.util.List;
import java.util.Map;



public interface LuaServices {
    //Retornos Públicos
    LuaReturn runScript(String script, Map<String, Object> params, String path);

}
