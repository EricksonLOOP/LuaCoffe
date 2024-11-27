package com.edev.luabridge.Controllers.ApiController;

import com.edev.luabridge.DTOs.ApiDTOs.CriarApiDTO;
import com.edev.luabridge.DTOs.CriarRotaDTO.CriarRotaDTO;
import com.edev.luabridge.DTOs.LoginDTO.LoginDTO;
import com.edev.luabridge.DTOs.ScriptDTO.ScriptDTO;
import com.edev.luabridge.DTOs.UserDTOs.CreateUserDTO.CreateUserDTO;
import com.edev.luabridge.DTOs.UserDTOs.LoginUserDTO.LoginUserDTO;
import com.edev.luabridge.Modules.API.ApiServices;
import com.edev.luabridge.Modules.LuaServices.LuaServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/actions")
public class ApiController {
    private final ApiServices apiServices;
    private  final LuaServices luaServices;

    public ApiController(ApiServices apiServices, LuaServices luaServices) {
        this.apiServices = apiServices;
        this.luaServices = luaServices;
    }


    @PostMapping("/create/api")
    public ResponseEntity<?> criarApi(@RequestBody CriarApiDTO criarApiDTO){
        return apiServices.criarApi(criarApiDTO);
    }
    @DeleteMapping("delete/{userId}/{apiId}")
    public ResponseEntity<?> deleteApi(@PathVariable("userId")UUID user, @PathVariable("apiId") UUID apiId){
        return apiServices.deleteApi(user, apiId);
    }
    @PutMapping("/script/save/{scriptId}")
    public ResponseEntity<?> atualizarScript(@PathVariable("scriptId") UUID scriptId, @RequestBody ScriptDTO scriptDTO){
        return luaServices.atualizarScript(scriptId, scriptDTO);
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
