package org.homework.springhomework003.exception;

public class WrongInputException extends RuntimeException {
    public WrongInputException(String message) {
        super(message);
    }
}
