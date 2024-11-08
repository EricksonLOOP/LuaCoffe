package com.edev.luabridge.Services.LuaServices;

import com.edev.luabridge.DTOs.RequestDTO.RequestDTO;
import com.edev.luabridge.DTOs.ScriptDTO.ScriptDTO;
import com.edev.luabridge.Entities.LuaScriptEntity.LuaScriptEntity;
import org.luaj.vm2.LuaValue;
import org.springframework.http.ResponseEntity;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;


public interface LuaServices {
    //Retornos Públicos
    ResponseEntity<?> runScript(RequestDTO requestDTO);
    ResponseEntity<?> adicionarScript(LuaScriptEntity luaScriptEntity);
    ResponseEntity<?> deletarTudo();

    ResponseEntity<?> listaScripts();

    ResponseEntity<?> atualizarScript(UUID id, ScriptDTO scriptDTO);
}
