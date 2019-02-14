package ru.nsu.fit.shadrina.lexer;

public class Lexeme {

    private LexemeType lexemeType;
    private String text;

    Lexeme() {
        this.lexemeType = LexemeType.EMPTY;
        this.text = "";
    }

    Lexeme(LexemeType lexemeType, String text) {
        this.lexemeType = lexemeType;
        this.text = text;
    }

    public void setLexemeType(LexemeType lexemeType) {
        this.lexemeType = lexemeType;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LexemeType getLexemeType() {
        return lexemeType;
    }

    public String getText() {
        return text;
    }
}
