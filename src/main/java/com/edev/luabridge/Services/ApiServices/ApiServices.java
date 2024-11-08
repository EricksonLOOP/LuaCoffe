package com.edev.luabridge.Services.ApiServices;

import com.edev.luabridge.DTOs.CriarRotaDTO.CriarRotaDTO;
import com.edev.luabridge.DTOs.LoginDTO.LoginDTO;
import com.edev.luabridge.Entities.APIEntity.ApiEntity;
import com.edev.luabridge.Entities.LuaScriptEntity.LuaScriptEntity;
import org.springframework.http.ResponseEntity;

public interface ApiServices {
    ResponseEntity<?> criarApi(ApiEntity apiEntity);
    ResponseEntity<?> criarRota(CriarRotaDTO criarRotaDTO);

    ResponseEntity<?> loginApi(LoginDTO loginDTO);
}
