package com.edev.luabridge.Modules.APP;

import com.edev.luabridge.DTOs.RequestDTO.RequestDTO;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface AppServices {
    ResponseEntity<?> getController(String apiToken, String method, String route, Map<String, Object> params);
    ResponseEntity<?> postController(RequestDTO requestDTO);
    ResponseEntity<?> putController(RequestDTO requestDTO);
    ResponseEntity<?> deleteController(RequestDTO requestDTO);

    ResponseEntity<?> scriptController(RequestDTO requestDTO);
}
