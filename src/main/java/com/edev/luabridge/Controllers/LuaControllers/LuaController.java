package com.edev.luabridge.Controllers.LuaControllers;

import com.edev.luabridge.DTOs.RequestDTO.RequestDTO;
import com.edev.luabridge.DTOs.ScriptDTO.ScriptDTO;
import com.edev.luabridge.Entities.LuaScriptEntity.LuaScriptEntity;
import com.edev.luabridge.Services.LuaServices.LuaServices;
import org.luaj.vm2.LuaValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.UUID;

@RestController
@RequestMapping("/api/lua")
@CrossOrigin(originPatterns = "http://localhost:5173/")
public class LuaController {
    @Autowired
    final private LuaServices luaServices;

    public LuaController(LuaServices luaServices) {
        this.luaServices = luaServices;
    }

    @PostMapping("/execute/script")
    public ResponseEntity<?> RetornoScript(@RequestBody RequestDTO requestDTO)  {
        return luaServices.runScript(requestDTO);
    }
    @PostMapping("/create")
    public ResponseEntity<?> adicionarScript(@RequestBody LuaScriptEntity luaScriptEntity){
        return luaServices.adicionarScript(luaScriptEntity);
    }
    @DeleteMapping("/scripts/deleteall")
    public ResponseEntity<?> deletarTudo(){
        return luaServices.deletarTudo();
    }
    @PatchMapping("/save/{id}")
    public ResponseEntity<?> atualizarScript(@PathVariable("id") UUID id, @RequestBody ScriptDTO scriptDTO){
        return luaServices.atualizarScript(id, scriptDTO);
    }
    @GetMapping("/scripts/list")
    public ResponseEntity<?> listaScripts()  {
        return luaServices.listaScripts();
    }
}
