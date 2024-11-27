package com.edev.luabridge.Modules.email;

public class EnviarEmailServicesImpl implements EnviarEmailServices{

    @Override
    public void enviarEmailVerificacaoConta(String reciver) {
        try{
            MailSenderFeat sender = new MailSenderFeat();
            EmailServiceImpl emailService=  new EmailServiceImpl(sender.getJavaMailSender());
            StringBuilder text = new StringBuilder();
            text.append("Olá, Muito obrigado por testar o LuaCoffe!\n")
                    .append("Por favor, ative sua conta para que seja registrada em nosso serviço.");
            emailService.sendSimpleMessage(reciver, "Erickson do LuaCoffe - Ativação de Conta", " " );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
