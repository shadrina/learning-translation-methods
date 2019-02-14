package ru.nsu.fit.shadrina.exceptions;

import org.jetbrains.annotations.Contract;

public enum ParserExceptionType {
    NO_EOF("No EOF at the end of the file"),
    NO_CLOSING_BRACKET("No closing bracket"),
    UNKNOWN_ATOM("Unknown atom")
    ;

    private String message;

    ParserExceptionType(String message) {
        this.message = message;
    }

    @Contract(pure = true)
    public String getMessage() {
        return message;
    }
}
