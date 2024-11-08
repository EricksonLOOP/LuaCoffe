package com.edev.luabridge.Services.ApiServices;

import com.edev.luabridge.DTOs.ApiEntityDTO.ApiEntityDTO;
import com.edev.luabridge.DTOs.CriarRotaDTO.CriarRotaDTO;
import com.edev.luabridge.DTOs.LoginDTO.LoginDTO;
import com.edev.luabridge.DTOs.LuaScriptDTO.LuaScriptDTO;
import com.edev.luabridge.Entities.APIEntity.ApiEntity;
import com.edev.luabridge.Entities.LuaScriptEntity.LuaScriptEntity;
import com.edev.luabridge.Models.RouteTypeModel.RouteType;
import com.edev.luabridge.Repositories.ApiRepository;
import com.edev.luabridge.Repositories.LuaRepository;
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
    public ApiServicesImpl(ApiRepository apiRepository, LuaRepository luaRepository) {
        this.apiRepository = apiRepository;
        this.luaRepository = luaRepository;
    }

    @Override
    public ResponseEntity<?> criarApi(ApiEntity apiEntity) {
        try {
            Optional<ApiEntity> optionalApiEntity = apiRepository.findByName(apiEntity.getName());
            if (optionalApiEntity.isPresent()){
                return ResponseEntity.badRequest().body("Uma API com o mesmo nome já existe!");
            }
            ApiEntity novaApi =  ApiEntity.builder()
                    .name(apiEntity.getName())
                    .apiToken(gerarToken())
                    .build();
            return ResponseEntity.ok().body(apiRepository.save(novaApi));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
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
                        .filter(rota -> rota.getRoute().equals(criarRotaDTO.name()) && rota.getRoute().equals(criarRotaDTO.route()))
                        .findFirst();

                if (rotaIgual.isPresent()) {
                    return ResponseEntity.badRequest().body("Já existe uma rota " + criarRotaDTO.route() + " com o nome " + criarRotaDTO.name());
                }

                // Criar a nova rota
                LuaScriptEntity novoScript = LuaScriptEntity.builder()
                        .method(RouteType.valueOf(criarRotaDTO.route()))
                        .route(criarRotaDTO.name())
                        .apiEntity(apiEntity)
                        .build();

                luaScriptEntityList.add(novoScript); // Adiciona a nova rota
                luaRepository.save(novoScript); // Salva o script no repositório
                apiRepository.save(apiEntity); // Atualiza a entidade API

                return ResponseEntity.ok().body("Rota criada com sucesso!");
            }

            // Token inválido
            return ResponseEntity.badRequest().body("Token inválido.");

        } catch (IllegalArgumentException e) {
            // Captura exceções como uma RouteType inválida e outras exceções esperadas
            return ResponseEntity.badRequest().body("Erro ao processar a rota: " + e.getMessage());
        } catch (Exception e) {
            // Exceção genérica para outros tipos de erros
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado ao criar a rota.");
        }
    }


    @Override
    public ResponseEntity<?> loginApi(LoginDTO loginDTO) {
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
