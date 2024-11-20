package com.edev.luabridge.Services.ApiServices;

import com.edev.luabridge.DTOs.ApiDTOs.CriarApiDTO;
import com.edev.luabridge.DTOs.CriarRotaDTO.CriarRotaDTO;
import com.edev.luabridge.DTOs.LoginDTO.LoginDTO;
import com.edev.luabridge.DTOs.UserDTOs.CreateUserDTO.CreateUserDTO;
import com.edev.luabridge.DTOs.UserDTOs.LoginUserDTO.LoginUserDTO;
import com.edev.luabridge.Entities.APIEntity.ApiEntity;
import com.edev.luabridge.Entities.LuaScriptEntity.LuaScriptEntity;
import org.springframework.http.ResponseEntity;

public interface ApiServices {
    ResponseEntity<?> criarApi(CriarApiDTO criarApiDTO);
    ResponseEntity<?> criarRota(CriarRotaDTO criarRotaDTO);

    ResponseEntity<?> getApi(LoginDTO loginDTO);
ResponseEntity<?> loginUser(LoginUserDTO loginUserDTO);
    ResponseEntity<?> createUser(CreateUserDTO createUserDTO);
}
