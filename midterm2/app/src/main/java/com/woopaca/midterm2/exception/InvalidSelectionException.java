package com.woopaca.midterm2.exception;

public class InvalidSelectionException extends RuntimeException {

    private final String message;

    public InvalidSelectionException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
