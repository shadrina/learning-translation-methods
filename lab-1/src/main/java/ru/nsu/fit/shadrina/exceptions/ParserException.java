package ru.nsu.fit.shadrina.exceptions;

public class ParserException extends Exception {

    private ParserExceptionType type;

    public ParserException() {}

    public ParserException(ParserExceptionType type) {
        super(type.getMessage());
        this.type = type;
    }

    public ParserExceptionType getType() {
        return type;
    }
}
