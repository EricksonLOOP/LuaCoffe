package com.edev.luabridge.Modules.email;

import org.springframework.mail.javamail.JavaMailSender;

public interface EnviarEmailServices {
    void enviarEmailVerificacaoConta(String reciver);
    JavaMailSender criarSender(String email, String password);
    Boolean enviarMensagem(String reciver, String subject, String text);


}
