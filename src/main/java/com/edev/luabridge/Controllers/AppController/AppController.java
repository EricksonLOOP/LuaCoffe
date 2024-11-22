package com.edev.luabridge.Controllers.AppController;

import com.edev.luabridge.DTOs.RequestDTO.RequestDTO;
import com.edev.luabridge.Services.AppServices.AppServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/luabridge")
public class AppController {
    private  final AppServices appServices;

    public AppController(AppServices appServices) {
        this.appServices = appServices;
    }

    @GetMapping("/get/api")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<?> getController(@RequestParam String apiToken, @RequestParam String method, @RequestParam String route){
        //@RequestParam List<Map<String, Object>> param
    return appServices.getController(apiToken, method, route, Collections.emptyList());
    }
    @PostMapping("/post/api")
    public ResponseEntity<?> postController(@RequestBody RequestDTO requestDTO){
        return appServices.postController(requestDTO);
    }
    @PostMapping("/script/api")
    public ResponseEntity<?> scriptController(@RequestBody RequestDTO requestDTO){
        return appServices.scriptController(requestDTO);
    }
    @PutMapping("/put/api")
    public ResponseEntity<?> putController(@RequestBody RequestDTO requestDTO){
        return appServices.putController(requestDTO);
    }
    @DeleteMapping("/delete/api")
    public ResponseEntity<?> deleteController(@RequestBody RequestDTO requestDTO){
        return appServices.deleteController(requestDTO);
    }
}
