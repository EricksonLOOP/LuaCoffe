package com.edev.luabridge.Services.AppServices;

import com.edev.luabridge.DTOs.LuaScriptDTO.LuaScriptDTO;
import com.edev.luabridge.DTOs.RequestDTO.RequestDTO;
import com.edev.luabridge.Entities.APIEntity.ApiEntity;
import com.edev.luabridge.Entities.LuaScriptEntity.LuaScriptEntity;
import com.edev.luabridge.Models.RouteTypeModel.RouteType;
import com.edev.luabridge.Repositories.ApiRepository;
import com.edev.luabridge.Services.LuaServices.LuaServices;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AppServicesImpl implements AppServices {
    @Autowired
    private final ApiRepository apiRepository;
    @Autowired
    private final LuaServices luaServices;
    public AppServicesImpl(ApiRepository apiRepository, LuaServices luaServices) {
        this.apiRepository = apiRepository;
        this.luaServices = luaServices;
    }

    @Override
    public ResponseEntity<?> getController(String apiToken, String method, String route, Map<String, Object> objects) {
        try {
            if (!method.equalsIgnoreCase("GET")){
                return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                        .body("Método não permitido. Esperado: GET");
            }

            Optional<ApiEntity> optionalApiEntity = apiRepository.findByApiToken(apiToken);
            if (optionalApiEntity.isEmpty()) {
                return ResponseEntity.badRequest().body("Token inválido ou não encontrado.");
            }

            ApiEntity api = optionalApiEntity.get();
            Optional<LuaScriptEntity> script = api.getRotas().stream()
                    .filter(rota -> rota.getRoute().equals(route)
                            && rota.getMethod().equals(RouteType.valueOf(method)))
                    .findFirst();

            if (script.isPresent()) {
                JSONObject objParams = new JSONObject(objects.get("objects").toString());
                List<Map<String, Object>> objectList = new ArrayList<>();
                Map<String,Object> fromJsonToMap = objParams.toMap();

                for (Map.Entry<String, Object> entry : fromJsonToMap.entrySet()) {
                    Map<String, Object> singleMap = new HashMap<>();
                    singleMap.put(entry.getKey(), entry.getValue());
                    objectList.add(singleMap);
                }
                RequestDTO nreq = new RequestDTO(route, apiToken, method, objectList);
                return luaServices.runScript(nreq);
            }

            return ResponseEntity.badRequest().body("Script não encontrado para a rota especificada.");
        } catch (Exception e) {
            // Aqui você pode logar a exceção
            // logger.error("Erro ao processar request: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno ao processar a solicitação. Message: "+e.getMessage());
        }
    }


    @Override
    public ResponseEntity<?> postController(RequestDTO requestDTO) {
        try {
            if (!requestDTO.method().equalsIgnoreCase("POST")){
                return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                        .body("Método não permitido. Esperado: POST");
            }

            Optional<ApiEntity> optionalApiEntity = apiRepository.findByApiToken(requestDTO.apiToken());
            if (optionalApiEntity.isEmpty()) {
                return ResponseEntity.badRequest().body("Token inválido ou não encontrado.");
            }

            ApiEntity api = optionalApiEntity.get();
            Optional<LuaScriptEntity> script = api.getRotas().stream()
                    .filter(rota -> rota.getMethod().equals(RouteType.valueOf(requestDTO.method()))
                            && rota.getRoute().equals(requestDTO.route()))
                    .findFirst();

            if (script.isPresent()) {
                return luaServices.runScript(requestDTO);
            }

            return ResponseEntity.badRequest().body("Script não encontrado para a rota especificada.");
        } catch (Exception e) {
            // Aqui você pode logar a exceção
            // logger.error("Erro ao processar request: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno ao processar a solicitação. Error: "+e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> putController(RequestDTO requestDTO) {
        try {
            if (!requestDTO.route().equalsIgnoreCase("PUT")){
                return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                        .body("Método não permitido. Esperado: PUT");
            }

            Optional<ApiEntity> optionalApiEntity = apiRepository.findByApiToken(requestDTO.apiToken());
            if (optionalApiEntity.isEmpty()) {
                return ResponseEntity.badRequest().body("Token inválido ou não encontrado.");
            }

            ApiEntity api = optionalApiEntity.get();
            Optional<LuaScriptEntity> script = api.getRotas().stream()
                    .filter(rota -> rota.getRoute().equals(RouteType.valueOf(requestDTO.route()))
                            && rota.getRoute().equals(requestDTO.route()))
                    .findFirst();

            if (script.isPresent()) {
                return luaServices.runScript(requestDTO);
            }

            return ResponseEntity.badRequest().body("Script não encontrado para a rota especificada.");
        } catch (Exception e) {
            // Aqui você pode logar a exceção
            // logger.error("Erro ao processar request: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno ao processar a solicitação.");
        }
    }

    @Override
    public ResponseEntity<?> deleteController(RequestDTO requestDTO) {
        try {
            if (!requestDTO.route().equalsIgnoreCase("DELETE")){
                return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                        .body("Método não permitido. Esperado: DELETE");
            }

            Optional<ApiEntity> optionalApiEntity = apiRepository.findByApiToken(requestDTO.apiToken());
            if (optionalApiEntity.isEmpty()) {
                return ResponseEntity.badRequest().body("Token inválido ou não encontrado.");
            }

            ApiEntity api = optionalApiEntity.get();
            Optional<LuaScriptEntity> script = api.getRotas().stream()
                    .filter(rota -> rota.getRoute().equals(RouteType.valueOf(requestDTO.route()))
                            && rota.getRoute().equals(requestDTO.route()))
                    .findFirst();

            if (script.isPresent()) {
                return luaServices.runScript(requestDTO);
            }

            return ResponseEntity.badRequest().body("Script não encontrado para a rota especificada.");
        } catch (Exception e) {
            // Aqui você pode logar a exceção
            // logger.error("Erro ao processar request: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno ao processar a solicitação.");
        }
    }

    @Override
    public ResponseEntity<?> scriptController(RequestDTO requestDTO) {
        try {
            if (!requestDTO.method().equalsIgnoreCase("SCRIPT")){
                return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                        .body("Método não permitido. Esperado: POST => SCRIPT");
            }

            Optional<ApiEntity> optionalApiEntity = apiRepository.findByApiToken(requestDTO.apiToken());
            if (optionalApiEntity.isEmpty()) {
                return ResponseEntity.badRequest().body("Token inválido ou não encontrado.");
            }

            ApiEntity api = optionalApiEntity.get();
            Optional<LuaScriptEntity> script = api.getRotas().stream()
                    .filter(rota -> rota.getMethod().equals(RouteType.valueOf(requestDTO.method()))
                            && rota.getRoute().equals(requestDTO.route()))
                    .findFirst();

            if (script.isPresent()) {
                return luaServices.runScript(requestDTO);
            }

            return ResponseEntity.badRequest().body("Script não encontrado para a rota especificada.");
        } catch (Exception e) {
            // Aqui você pode logar a exceção
            // logger.error("Erro ao processar request: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno ao processar a solicitação.");
        }
    }
}
