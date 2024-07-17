package br.com.forumhub.challenge.forumhub.infra.exception;

public class ValidationException extends RuntimeException{
    public ValidationException(String message) {
        super(message);
    }
}