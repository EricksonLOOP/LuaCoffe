package com.edev.luabridge.Modules.api;


import com.edev.luabridge.Modules.Exceptions.ResourceNotFoundException;
import com.edev.luabridge.Modules.File.FileServices;
import com.edev.luabridge.Modules.LuaServices.LuaServices;
import com.edev.luabridge.Modules.Utils.MainControllerUtils;
import com.edev.luabridge.Modules.api.Models.FileAndPathModel;
import com.edev.luabridge.Modules.api.Models.LuaReturn;
import jakarta.servlet.http.HttpServletRequest;
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
    @Autowired
    private final MainControllerUtils mainControllerUtils;
    public LuaCoffeRequestController(LuaServices luaServices, FileServices fileServices, MainControllerUtils mainControllerUtils) {
        this.luaServices = luaServices;
        this.fileServices = fileServices;
        this.mainControllerUtils = mainControllerUtils;
    }

    @GetMapping("/get/**")
    public ResponseEntity<?> LuaCoffeGet(
            HttpServletRequest request
    ) {
        try{
            FileAndPathModel fileAndPath = mainControllerUtils.getFileByRoute("get", request);
            LuaReturn luaReturn = luaServices.runScriptApi(fileAndPath.getFile(), Collections.emptyMap(), fileAndPath.getPath());
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(luaReturn.getReturnCode()))
                    .body(mainControllerUtils.getReturnValue(luaReturn).toString());
        }catch (NullPointerException | ResourceNotFoundException e ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch (IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while reading the script");
        }

    }
    @GetMapping("/pages/**")
    public ResponseEntity<?> LuaCoffePages(
            HttpServletRequest request
    ) {
        try{
            FileAndPathModel fileAndPath = mainControllerUtils.getFileByRoute("get", request);
            LuaReturn luaReturn = luaServices.runScriptApi(fileAndPath.getFile(), Collections.emptyMap(), fileAndPath.getPath());
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(luaReturn.getReturnCode()))
                    .body(mainControllerUtils.getReturnValue(luaReturn).toString());
        }catch (NullPointerException | ResourceNotFoundException e ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch (IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while reading the script");
        }
    }

    @PostMapping("/post/**")
    public ResponseEntity<?> LuaCoffePost(
            HttpServletRequest request,
            @RequestBody Map<String, Object> params){

        try{
            FileAndPathModel fileAndPath = mainControllerUtils.getFileByRoute("post", request);
            LuaReturn luaReturn = luaServices.runScriptApi(fileAndPath.getFile(), params, fileAndPath.getPath());
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(luaReturn.getReturnCode()))
                    .body(mainControllerUtils.getReturnValue(luaReturn).toString());
        }catch (NullPointerException | ResourceNotFoundException e ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch (IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while reading the script");
        }
    }
    @PutMapping("/put/**")
    public ResponseEntity<?> LuaCoffePut(
            HttpServletRequest request,
            @RequestBody Map<String, Object> params){

        try{
            FileAndPathModel fileAndPath = mainControllerUtils.getFileByRoute("put", request);
            LuaReturn luaReturn = luaServices.runScriptApi(fileAndPath.getFile(), params, fileAndPath.getPath());
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(luaReturn.getReturnCode()))
                    .body(mainControllerUtils.getReturnValue(luaReturn).toString());
        }catch (NullPointerException | ResourceNotFoundException e ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch (IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while reading the script");
        }
    }
    @DeleteMapping("/delete/**")
    public ResponseEntity<?> LuaCoffeDelete(
            HttpServletRequest request,
            @RequestBody Map<String, Object> params) throws IOException {

        try{
            FileAndPathModel fileAndPath = mainControllerUtils.getFileByRoute("delete", request);
            LuaReturn luaReturn = luaServices.runScriptApi(fileAndPath.getFile(), params, fileAndPath.getPath());
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(luaReturn.getReturnCode()))
                    .body(mainControllerUtils.getReturnValue(luaReturn).toString());
        }catch (NullPointerException | ResourceNotFoundException e ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch (IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while reading the script");
        }
    }


    @GetMapping("/hello")
    public String hello(){
        return "Hello,World!";
    }


}
