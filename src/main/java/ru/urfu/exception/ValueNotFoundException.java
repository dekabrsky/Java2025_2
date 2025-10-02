package ru.urfu.exception;

public class ValueNotFoundException extends RuntimeException {
    public ValueNotFoundException(String message) {
        super(message);
    }
}
