package com.edev.luabridge.Controllers.ApiController;

import com.edev.luabridge.DTOs.ApiDTOs.CriarApiDTO;
import com.edev.luabridge.DTOs.CriarRotaDTO.CriarRotaDTO;
import com.edev.luabridge.DTOs.LoginDTO.LoginDTO;
import com.edev.luabridge.DTOs.UserDTOs.CreateUserDTO.CreateUserDTO;
import com.edev.luabridge.DTOs.UserDTOs.LoginUserDTO.LoginUserDTO;
import com.edev.luabridge.Entities.APIEntity.ApiEntity;
import com.edev.luabridge.Services.ApiServices.ApiServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/actions")
public class ApiController {
    private final ApiServices apiServices;

    public ApiController(ApiServices apiServices) {
        this.apiServices = apiServices;
    }

    @PostMapping("/create/api")
    public ResponseEntity<?> criarApi(@RequestBody CriarApiDTO criarApiDTO){
        return apiServices.criarApi(criarApiDTO);
    }
    @DeleteMapping("delete/{userId}/{apiId}")
    public ResponseEntity<?> deleteApi(@PathVariable("userId")UUID user, @PathVariable("apiId") UUID apiId){
        return apiServices.deleteApi(user, apiId);
    }
    @PostMapping("/add/rota")
    public ResponseEntity<?> criarRota(@RequestBody CriarRotaDTO criarRotaDTO){
        return apiServices.criarRota(criarRotaDTO);
    }
    @PostMapping("/get/api")
    public ResponseEntity<?> loginApi(@RequestBody LoginDTO loginDTO){
        return apiServices.getApi(loginDTO);
    }
    @PostMapping("/create/user")
    public ResponseEntity<?> createUser(@RequestBody CreateUserDTO createUserDTO){
        return apiServices.createUser(createUserDTO);
    }
    @PostMapping("/login/user")
    public ResponseEntity<?> loginUser(@RequestBody LoginUserDTO loginUserDTO){
        return apiServices.loginUser(loginUserDTO);
    }
}
