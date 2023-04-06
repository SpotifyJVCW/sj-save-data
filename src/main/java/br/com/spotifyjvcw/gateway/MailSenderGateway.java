package br.com.spotifyjvcw.gateway;

public interface MailSenderGateway {

    void execute(String addressSender, String bodyMessage, String subject);
}
