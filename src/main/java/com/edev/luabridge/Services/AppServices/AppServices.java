package com.edev.luabridge.Services.AppServices;

import com.edev.luabridge.DTOs.RequestDTO.RequestDTO;
import org.springframework.http.ResponseEntity;

public interface AppServices {
    ResponseEntity<?> getController(String apiToken, String method, String route);
    ResponseEntity<?> postController(RequestDTO requestDTO);
    ResponseEntity<?> putController(RequestDTO requestDTO);
    ResponseEntity<?> deleteController(RequestDTO requestDTO);

}
