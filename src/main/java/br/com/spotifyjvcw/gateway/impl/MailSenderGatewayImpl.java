package br.com.spotifyjvcw.gateway.impl;

import br.com.spotifyjvcw.config.MailProperties;
import br.com.spotifyjvcw.gateway.MailSenderGateway;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MailSenderGatewayImpl implements MailSenderGateway {

    private final MailProperties mailProperties;
    @Override
    public void execute(String addressSender, String bodyMessage, String subject) {
        try {
            Message message = new MimeMessage(mailProperties.getSession());
            message.setFrom(new InternetAddress(mailProperties.getAddress()));

            message.setRecipient(Message.RecipientType.TO, InternetAddress.parse(addressSender)[0]);
            message.setSubject(subject);
            message.setText(bodyMessage);

            Transport.send(message);
            log.info("Mensagem enviada com sucesso!");
        } catch (MessagingException e) {
            log.error("Erro ao enviar email | Causa: {}", e.getMessage());
        }
    }
}
