package ru.nsu.fit.shadrina.exceptions;

public class ParserException extends Exception {
    public ParserException() {}

    public ParserException(ParserExceptionType type) {
        super(type.getMessage());
    }
}
