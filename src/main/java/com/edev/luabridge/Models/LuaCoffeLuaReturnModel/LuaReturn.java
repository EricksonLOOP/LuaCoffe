package com.edev.luabridge.Models.LuaCoffeLuaReturnModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.luaj.vm2.LuaValue;
// Objeto de retorno para os endpoints
@Data
@AllArgsConstructor
public class LuaReturn {
    private int returnCode;
    private LuaValue returnObj;
}
