package com.edev.luabridge.Modules.LuaCoffeRequests;

import com.edev.luabridge.Models.LuaCoffeLuaReturnModel.LuaReturn;
import com.edev.luabridge.Modules.File.FileServices;
import com.edev.luabridge.Modules.LuaServices.LuaServices;
import jakarta.servlet.http.HttpServletRequest;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;


@RestController
@RequestMapping("/api")
// This RestController is responsible to receive all requests and process
// With the lua scripts in the Framework
public class LuaCoffeRequestController {
    @Autowired
    private final LuaServices luaServices;
    @Autowired
    private final FileServices fileServices;
    public LuaCoffeRequestController(LuaServices luaServices, FileServices fileServices) {
        this.luaServices = luaServices;
        this.fileServices = fileServices;
    }

    @GetMapping("/get/**")
    public ResponseEntity<?> LuaCoffeGet(
            HttpServletRequest request
    ) throws IOException, URISyntaxException {

        String endpoint = request.getRequestURI().substring(request.getContextPath().length() + "/get/".length());
        String luascript = endpoint.substring(endpoint.lastIndexOf('/') + 1);
        Optional<File> fileOpt = Optional.ofNullable(fileServices.encontrarArquivos(luascript, "get"));
        if (fileOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Script não encontrado");
        }

        File scriptFile = fileOpt.get();
        String readFile = fileServices.readFile(scriptFile);
        if (readFile.isEmpty()) {
            return ResponseEntity.badRequest().body("Script está vazio");
        }

        LuaReturn luaReturn = luaServices.runScript(readFile, Collections.emptyList(), endpoint);
        return ResponseEntity
                .status(HttpStatusCode.valueOf(luaReturn.getReturnCode()))
                .body(getReturnValue(luaReturn));
    }

    @PostMapping("/post/**")
    public ResponseEntity<?> LuaCoffePost(
            HttpServletRequest request,
            @RequestBody Map<String, List<Map<String, Object>>> body) throws IOException {

        try{

            String endpoint = request.getRequestURI().substring(request.getContextPath().length() + "/get/".length());
            String luascript = endpoint.substring(endpoint.lastIndexOf('/') + 1);
            List<Map<String, Object>> params = body.get("params");
            String[] paths = endpoint.split("/");
            Optional<File> file = Optional.ofNullable(fileServices.encontrarArquivos(luascript, "post"));

            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endpoint not found");
            }

            File scriptFile = file.get();
            String readFile = fileServices.readFile(scriptFile);

            if (readFile.isEmpty()) {
                return ResponseEntity.badRequest().body("Script está vazio");
            }
            LuaReturn luaReturn = luaServices.runScript(readFile, params, endpoint);
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(luaReturn.getReturnCode()))
                    .body(getReturnValue(luaReturn));
        }catch (NullPointerException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endpoint not found.");
        }
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello,World!";
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
