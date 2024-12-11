package com.edev.luabridge.Modules.email;

import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;

public interface EmailServices {
    void enviarEmailVerificacaoConta(String reciver);
    JavaMailSender criarSender(String email, String password);
    Boolean enviarMensagem(String reciver, String subject, String text);
    ResponseEntity<?> cadastrarEmailServices(String email);


}
