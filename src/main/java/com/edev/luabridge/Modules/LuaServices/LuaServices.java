package com.edev.luabridge.Modules.LuaServices;

import com.edev.luabridge.Models.LuaCoffeLuaReturnModel.LuaReturn;

import java.util.Map;



public interface LuaServices {
    //Retornos Públicos
    LuaReturn runScriptApi(String script, Map<String, Object> params, String path);
    LuaReturn runScriptPages(String script, Map<String, Object> params, String path, String action);

}
