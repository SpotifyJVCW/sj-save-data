package br.com.spotifyjvcw.exception.especific;

public class ClientAlreadyExistsException extends RuntimeException{
    public ClientAlreadyExistsException(String message, Throwable throwable){
        super(message, throwable);
    }

    public ClientAlreadyExistsException(String message){
        super(message);
    }
}
