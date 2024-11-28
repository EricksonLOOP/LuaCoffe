package com.edev.luabridge.Modules.email;

import com.edev.luabridge.Modules.CriarLinks.CriarLinksServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EnviarEmailServicesImpl implements EnviarEmailServices{
    @Autowired
    final private JavaMailSender mailSender;
    @Autowired
    final private CriarLinksServices criarLinksServices;

    public EnviarEmailServicesImpl(JavaMailSender mailSender, CriarLinksServices criarLinksServices) {
        this.criarLinksServices = criarLinksServices;
        this.mailSender = criarSender("ericksonagust@gmail.com", "uwlk tfup xehr etck");
    }

    @Override
    public void enviarEmailVerificacaoConta(String reciver) {
        try{
            StringBuilder text = new StringBuilder();
            text.append("Olá, Muito obrigado por testar o LuaCoffe!\n")
                    .append("Por favor, ative sua conta para que seja registrada em nosso serviço.\n")
                    .append("Accesse: ").append(criarLinksServices.criarLinkVerificacao(reciver)).append("")
            ;
          enviarMensagem(reciver, "Erickson do LuaCoffe - Ativação de Conta", text.toString() );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JavaMailSender criarSender(String email, String password) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername(email);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    @Override
    public Boolean enviarMensagem(String reciver, String subject, String text) {
        try{

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("ericksonagust45@gmail.com");
            message.setTo(reciver);
            message.setSubject(subject);
            message.setText(text);
            mailSender.send(message);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
