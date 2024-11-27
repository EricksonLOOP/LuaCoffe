package com.edev.luabridge.Modules.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl  {
    @Autowired
    private JavaMailSender sender;
    public EmailServiceImpl(JavaMailSender sender){
        this.sender = sender;
    }
    public void sendSimpleMessage(String reciver, String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ericksonagust45@gmail.com");
        message.setTo(reciver);
        message.setSubject(subject);
        message.setText(text);
        sender.send(message);
    }
}
