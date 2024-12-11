package com.edev.luabridge.Controllers.ServicesController;

import com.edev.luabridge.Modules.email.EmailServices;
import com.edev.luabridge.Repositories.EmailRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/services")
public class ServicesController {
    @Autowired
    private final EmailRepository emailRepository;
    @Autowired
    private final EmailServices emailServices;

    public ServicesController(EmailRepository emailRepository, EmailServices emailServices) {
        this.emailRepository = emailRepository;
        this.emailServices = emailServices;
    }

    @PostMapping("/email/register")
    public ResponseEntity<?> registrarEmailNosServicos(
            @RequestBody
            @Valid
            @Email(message = "Coloque um email válido.")
            @NotNull(message = "Email não pode ser nulo.")
            String email){
    return emailServices.cadastrarEmailServices(email);
    }
}
