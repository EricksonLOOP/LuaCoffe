package com.edev.luabridge.Models.LuaCoffeLuaReturnModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.luaj.vm2.LuaValue;

@Data
@AllArgsConstructor
public class LuaReturn {
    private int returnCode;
    private LuaValue returnObj;
}
