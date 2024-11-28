package com.edev.luabridge.Modules.API;

import com.edev.luabridge.DTOs.ApiDTOs.CriarApiDTO;
import com.edev.luabridge.DTOs.CriarRotaDTO.CriarRotaDTO;
import com.edev.luabridge.DTOs.LoginDTO.LoginDTO;
import com.edev.luabridge.DTOs.UserDTOs.CreateUserDTO.CreateUserDTO;
import com.edev.luabridge.DTOs.UserDTOs.LoginUserDTO.LoginUserDTO;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface ApiServices {
    ResponseEntity<?> criarApi(CriarApiDTO criarApiDTO);
    ResponseEntity<?> criarRota(CriarRotaDTO criarRotaDTO);

    ResponseEntity<?> getApi(LoginDTO loginDTO);
    ResponseEntity<?> loginUser(LoginUserDTO loginUserDTO);
    ResponseEntity<?> createUser(CreateUserDTO createUserDTO);

    ResponseEntity<?> deleteApi(UUID user, UUID apiId);

    ResponseEntity<?> verificarConta(UUID token, String email);
}
