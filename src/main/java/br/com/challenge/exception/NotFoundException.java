package br.com.challenge.exception;

public class NotFoundException extends Exception {

    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }
}
