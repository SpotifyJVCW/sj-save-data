package br.com.spotifyjvcw.config;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Getter
@Setter
@Configuration
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties("mail-configuration")
public class MailProperties {

    private String host;
    private String port;
    private String socketFactory;
    private String socketFactoryPort;
    private String auth;
    private String address;
    private String password;

    public Properties getProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.socketFactory.class", socketFactory);
        properties.put("mail.smtp.socketFactory.port", socketFactoryPort);
        properties.put("mail.smtp.auth", auth);
        properties.put("mail.smtp.port", port);

        return properties;
    }

    public Session getSession() {
        return Session.getDefaultInstance(getProperties(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(address, password);
            }
        });
    }
}
