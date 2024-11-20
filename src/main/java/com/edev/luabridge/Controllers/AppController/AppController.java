package com.edev.luabridge.Controllers.AppController;

import com.edev.luabridge.DTOs.RequestDTO.RequestDTO;
import com.edev.luabridge.Services.AppServices.AppServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/luabridge")
public class AppController {
    private  final AppServices appServices;

    public AppController(AppServices appServices) {
        this.appServices = appServices;
    }
    @GetMapping("/get/api")
    public ResponseEntity<?> getController(@RequestParam String apiToken, @RequestParam String method, @RequestParam String route){
    return appServices.getController(apiToken, method, route);
    }
    @PostMapping("/post/api")
    public ResponseEntity<?> postController(@RequestBody RequestDTO requestDTO){
        return appServices.postController(requestDTO);
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
