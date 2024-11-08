package com.edev.luabridge.Controllers.ApiController;

import com.edev.luabridge.DTOs.CriarRotaDTO.CriarRotaDTO;
import com.edev.luabridge.DTOs.LoginDTO.LoginDTO;
import com.edev.luabridge.Entities.APIEntity.ApiEntity;
import com.edev.luabridge.Services.ApiServices.ApiServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/actions")
public class ApiController {
    private final ApiServices apiServices;

    public ApiController(ApiServices apiServices) {
        this.apiServices = apiServices;
    }

    @PostMapping("/create")
    public ResponseEntity<?> criarApi(@RequestBody ApiEntity apiEntity){
        return apiServices.criarApi(apiEntity);
    }
    @PostMapping("add/rota")
    public ResponseEntity<?> criarRota(@RequestBody CriarRotaDTO criarRotaDTO){
        return apiServices.criarRota(criarRotaDTO);
    }
    @PostMapping("/login")
    public ResponseEntity<?> criarRota(@RequestBody LoginDTO loginDTO){
        return apiServices.loginApi(loginDTO);
    }
}
