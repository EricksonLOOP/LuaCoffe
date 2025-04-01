package com.edev.luabridge.Modules.Utils;

import com.edev.luabridge.Modules.Exceptions.ResourceNotFoundException;
import com.edev.luabridge.Modules.File.FileServices;
import com.edev.luabridge.Modules.api.Models.FileAndPathModel;
import com.edev.luabridge.Modules.api.Models.LuaReturn;
import jakarta.servlet.http.HttpServletRequest;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class MainControllerUtils {
    @Autowired
    private final FileServices fileServices;

    public MainControllerUtils(FileServices fileServices) {
        this.fileServices = fileServices;
    }

    // Pegar o arquivo
    public FileAndPathModel getFileByRoute(String route, HttpServletRequest request) throws ResourceNotFoundException, NullPointerException, IOException {
        String endpoint = request.getRequestURI().substring(request.getContextPath().length() + "/post/".length());
        String luascript = endpoint.substring(endpoint.lastIndexOf('/') + 1);
        File file = Optional.ofNullable(fileServices.findFile(luascript, route)).orElseThrow(()-> new ResourceNotFoundException("Script not found"));
        String readFile = fileServices.readFile(file);
        return new FileAndPathModel(endpoint, readFile);
    }

    // Return value from LuaValue to JavaValues
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
   private Map<String, Object> luaTableToMap(LuaTable luaTable) {
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
