package com.edev.luabridge.Services.ApiServices;

import com.edev.luabridge.DTOs.ApiDTOs.ApiEntityDTO;
import com.edev.luabridge.DTOs.ApiDTOs.ApiRetornoDTO;
import com.edev.luabridge.DTOs.ApiDTOs.CriarApiDTO;
import com.edev.luabridge.DTOs.CriarRotaDTO.CriarRotaDTO;
import com.edev.luabridge.DTOs.LoginDTO.LoginDTO;
import com.edev.luabridge.DTOs.LuaScriptDTO.LuaScriptDTO;
import com.edev.luabridge.DTOs.UserDTOs.CreateUserDTO.CreateUserDTO;
import com.edev.luabridge.DTOs.UserDTOs.LoginUserDTO.LoginUserDTO;
import com.edev.luabridge.DTOs.UserDTOs.RetornoLoginDTO.RetornoLoginDTO;
import com.edev.luabridge.Entities.APIEntity.ApiEntity;
import com.edev.luabridge.Entities.LuaScriptEntity.LuaScriptEntity;
import com.edev.luabridge.Entities.UserEntity.UserEntity;
import com.edev.luabridge.Models.RouteTypeModel.RouteType;
import com.edev.luabridge.Repositories.ApiRepository;
import com.edev.luabridge.Repositories.LuaRepository;
import com.edev.luabridge.Repositories.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ApiServicesImpl implements ApiServices {
    @Autowired
    private final ApiRepository apiRepository;
    @Autowired
    private final LuaRepository luaRepository;
    @Autowired
    private  final UserRepository userRepository;
    public ApiServicesImpl(ApiRepository apiRepository, LuaRepository luaRepository, UserRepository userRepository) {
        this.apiRepository = apiRepository;
        this.luaRepository = luaRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<?> criarApi(CriarApiDTO criarApiDTO) {
        try {
            Optional<UserEntity> optionalUserEntity= userRepository.findById(criarApiDTO.id());
            if (optionalUserEntity.isEmpty()){
                return ResponseEntity.badRequest().body("Usuário não encontrado.");
            }
            UserEntity user = optionalUserEntity.get();
            Optional<ApiEntity> optionalApiEntity = user.getApis().stream()
                    .filter(api -> api.getName().equals(criarApiDTO.name())).findFirst();
            if (optionalApiEntity.isPresent()){
                return ResponseEntity.badRequest().body("Api com este nome ja existe");
            }
            ApiEntity novaApi =  ApiEntity.builder()
                    .name(criarApiDTO.name())
                    .user(user)
                    .apiToken(gerarToken())
                    .build();
            user.getApis().add(novaApi);
            apiRepository.save(novaApi);
            userRepository.save(user);
            List<ApiRetornoDTO> apisRetorno = new ArrayList<>();
            user.getApis().forEach(api -> apisRetorno.add(ApiRetornoDTO.builder()
                            .token(api.getApiToken())
                            .name(api.getName())
                            .id(api.getId())
                    .build()));
            RetornoLoginDTO retornoLoginDTO = RetornoLoginDTO.builder()
                    .apis(apisRetorno)
                    .email(user.getEmail())
                    .name(user.getName())
                    .id(user.getId())
                    .build();
            return ResponseEntity.ok().body(retornoLoginDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Erro desconhecido. "+e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> criarRota(CriarRotaDTO criarRotaDTO) {
        try {
            Optional<ApiEntity> optionalApiEntity = apiRepository.findByApiToken(criarRotaDTO.apiToken());
            if (optionalApiEntity.isPresent()) {
                ApiEntity apiEntity = optionalApiEntity.get();
                List<LuaScriptEntity> luaScriptEntityList = apiEntity.getRotas();

                // Verificar se já existe uma rota com o mesmo nome e tipo
                Optional<LuaScriptEntity> rotaIgual = luaScriptEntityList.stream()
                        .filter(rota -> rota.getRoute().equals(criarRotaDTO.route()) && rota.getMethod().equals(RouteType.valueOf(criarRotaDTO.method())))
                        .findFirst();

                if (rotaIgual.isPresent()) {
                    return ResponseEntity.badRequest().body("Já existe uma rota " + criarRotaDTO.method() + " com o nome " + criarRotaDTO.route());
                }

                // Criar a nova rota
                LuaScriptEntity novoScript = LuaScriptEntity.builder()
                        .method(RouteType.valueOf(criarRotaDTO.method()))
                        .route(criarRotaDTO.route())
                        .apiEntity(apiEntity)
                        .build();

                luaScriptEntityList.add(novoScript); // Adiciona a nova rota
                luaRepository.save(novoScript); // Salva o script no repositório
                apiRepository.save(apiEntity); // Atualiza a entidade API

                return ResponseEntity.ok().body("Rota criada com sucesso!");
            }

            return ResponseEntity.badRequest().body("Token inválido.");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro ao processar a rota: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado ao criar a rota. Message: "+e.getMessage());
        }
    }


    @Override
    public ResponseEntity<?> getApi(LoginDTO loginDTO) {
        try{
            Optional<ApiEntity> optionalApiEntity = apiRepository.findByApiToken(loginDTO.token());
            if (optionalApiEntity.isEmpty()){
                return ResponseEntity.badRequest().body("Api não encontrada");
            }

           ApiEntity api = optionalApiEntity.get();
            List<LuaScriptDTO> routes = new ArrayList<>();
            api.getRotas().stream()
                    .forEach(rota ->   routes.add(new LuaScriptDTO(
                            rota.getRoute(),
                            rota.getScript(),
                            rota.getMethod().toString(),
                            rota.getId())));

                    ApiEntityDTO apiDTO = new ApiEntityDTO(
                   api.getName(),
                    api.getId(), routes);
            return ResponseEntity.ok().body(apiDTO);
        }catch (RuntimeException e){
            throw new RuntimeException();
        }
    }

    @Override
    public ResponseEntity<?> loginUser(LoginUserDTO loginUserDTO) {
        try{
            Optional<UserEntity> optionalUserEntity = userRepository.findByEmail(loginUserDTO.email());
            if (optionalUserEntity.isEmpty()){
                return ResponseEntity.badRequest().body("Erro ao tentar fazer login, usuário não encontrado.");
            }
            List<ApiRetornoDTO> listApi = new ArrayList<>();
            UserEntity user = optionalUserEntity.get();
            user.getApis().forEach(api -> listApi.add( ApiRetornoDTO.builder()
                            .id(api.getId())
                            .name(api.getName())
                            .token(api.getApiToken())
                    .build()));
            RetornoLoginDTO nRetorno = RetornoLoginDTO.builder()
                    .name(user.getName())
                    .id(user.getId())
                    .email(user.getEmail())
                    .apis(listApi)
                    .build();
            return ResponseEntity.ok(nRetorno);
        }catch (Exception e){
            return  ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> createUser(CreateUserDTO createUserDTO) {
        try {
            Optional<UserEntity> optionalUserEntity = userRepository.findByEmail(createUserDTO.email());
            if (optionalUserEntity.isPresent()){
                return ResponseEntity.badRequest().body("Email ja registrado.");
            }
            UserEntity nUser = UserEntity.builder()
                    .email(createUserDTO.email())
                    .password(createUserDTO.password())
                    .build();
            return ResponseEntity.ok(userRepository.save(nUser));

        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Erro interno: "+e.getMessage());
        }
    }

    public String gerarToken(){
        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        final int TOKEN_LENGTH = 16;
        SecureRandom random = new SecureRandom();
        StringBuilder token = new StringBuilder(TOKEN_LENGTH);

        for (int i = 0; i < TOKEN_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            token.append(CHARACTERS.charAt(randomIndex));
        }

        return token.toString();
    }
}
