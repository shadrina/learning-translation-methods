package ru.nsu.fit.shadrina.lexer;

import com.sun.istack.internal.NotNull;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import ru.nsu.fit.shadrina.exceptions.LexerException;

import java.io.IOException;
import java.io.Reader;

public class Lexer {
    private static final Logger log = Logger.getLogger(Lexer.class);
    private Reader reader;
    private int current;

    public Lexer(@NotNull Reader reader) {
        this.reader = reader;
        try {
            this.current = reader.read();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public Lexeme getLexeme() throws LexerException, IOException {
        while (Character.isWhitespace(current)) {
            this.current = reader.read();
        }
        Lexeme l = new Lexeme();
        String text = String.valueOf((char)current);
        switch (text) {
            case "+":
                l.setLexemeType(LexemeType.PLUS);
                break;
            case "-":
                l.setLexemeType(LexemeType.MINUS);
                break;
            case "*":
                l.setLexemeType(LexemeType.MULT);
                break;
            case "/":
                l.setLexemeType(LexemeType.DIV);
                break;
            case "^":
                l.setLexemeType(LexemeType.POWER);
                break;
            case "(":
                l.setLexemeType(LexemeType.OPEN_BRACE);
                break;
            case ")":
                l.setLexemeType(LexemeType.CLOSE_BRACE);
                break;
            default:
                if (StringUtils.isNumeric(text)) {
                    StringBuilder numberText = new StringBuilder("");
                    while (StringUtils.isNumeric(String.valueOf((char)current))) {
                        numberText.append((char)current);
                        current = reader.read();
                    }
                    return new Lexeme(LexemeType.NUMBER, numberText.toString());
                }
                else if (current == -1) l.setLexemeType(LexemeType.EOF);
                else throw new LexerException("Unknown symbol");
        }
        l.setText(text);
        current = reader.read();
        return l;
    }


}
