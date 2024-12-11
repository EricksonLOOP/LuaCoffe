package com.edev.luabridge.Modules.email;

import com.edev.luabridge.Entities.ServicesEntitys.EmailServiceEntity.EmailServiceEntity;
import com.edev.luabridge.Modules.CriarLinks.CriarLinksServices;
import com.edev.luabridge.Repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Properties;

@Service
public class EmailServicesImpl implements EmailServices {
    @Autowired
    final private JavaMailSender mailSender;
    @Autowired
    final private CriarLinksServices criarLinksServices;
    @Autowired
    final private EmailRepository emailRepository;

    public EmailServicesImpl(JavaMailSender mailSender, CriarLinksServices criarLinksServices, EmailRepository emailRepository) {
        this.criarLinksServices = criarLinksServices;
        this.emailRepository = emailRepository;
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

    @Override
    public ResponseEntity<?> cadastrarEmailServices(String email) {
    try{
        Optional<EmailServiceEntity> optionalEmailServiceEntity = emailRepository.findByEmail(email);
        if (optionalEmailServiceEntity.isPresent()){
            return ResponseEntity.badRequest().body("Já registrado.");
        }
        EmailServiceEntity nEmail = EmailServiceEntity.builder()
                .email(email)
                .build();
        emailRepository.save(nEmail);
        return ResponseEntity.ok("Email registrado com sucesso.");
    } catch (Exception e) {
        return ResponseEntity.internalServerError().body("Erro interno ao salvar email no serviço.");
    }
    }
}
