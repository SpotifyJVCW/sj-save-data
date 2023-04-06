package br.com.spotifyjvcw.usecase;

import org.springframework.scheduling.annotation.Async;

public interface SendMail {

    @Async
    void whenChangePositions(String clientId);
}
