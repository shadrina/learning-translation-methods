package ru.nsu.fit.shadrina.parser;

import org.apache.log4j.Logger;
import ru.nsu.fit.shadrina.exceptions.LexerException;
import ru.nsu.fit.shadrina.exceptions.ParserException;
import ru.nsu.fit.shadrina.lexer.Lexeme;
import ru.nsu.fit.shadrina.lexer.Lexer;

import java.io.IOException;

import static ru.nsu.fit.shadrina.lexer.LexemeType.*;

public class Parser {
    private static final Logger log = Logger.getLogger(Parser.class);
    private Lexer lexer;
    private Lexeme current;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        try {
            this.current = lexer.getLexeme();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public int parse() throws LexerException, ParserException, IOException {
        int result = parseExpression();
        if (lexer.getLexeme().getLexemeType() != EOF) {
            throw new ParserException();
        }
        return result;
    }

    private int parseExpression() throws LexerException, ParserException, IOException {
        int temp = parseTerm();
        while (current.getLexemeType() == PLUS || current.getLexemeType() == MINUS) {
            if (current.getLexemeType() == PLUS) {
                current = lexer.getLexeme();
                temp += parseTerm();
            }
            if (current.getLexemeType() == MINUS) {
                current = lexer.getLexeme();
                temp -= parseTerm();
            }
        }
        return temp;
    }

    private int parseTerm() throws LexerException, ParserException, IOException {
        int temp = parseFactor();
        while (current.getLexemeType() == MULT || current.getLexemeType() == DIV) {
            if (current.getLexemeType() == MULT) {
                current = lexer.getLexeme();
                temp *= parseFactor();
            }
            if (current.getLexemeType() == DIV) {
                current = lexer.getLexeme();
                temp /= parseFactor();
            }
        }
        return temp;
    }

    private int parseFactor() throws LexerException, ParserException, IOException {
        int temp = parsePower();
        if (current.getLexemeType() == POWER) {
            current = lexer.getLexeme();
            temp = (int)Math.pow(temp, parseFactor());
        }
        return temp;
    }

    private int parsePower() throws LexerException, ParserException, IOException {
        if (current.getLexemeType() == MINUS) {
            current = lexer.getLexeme();
            return -parseAtom();
        }
        return parseAtom();
    }

    private int parseAtom() throws LexerException, ParserException, IOException {
        if (current.getLexemeType() == NUMBER) {
            int value = Integer.parseInt(current.getText());
            current = lexer.getLexeme();
            return value;
        }
        if (current.getLexemeType() == OPEN_BRACE) {
            current = lexer.getLexeme();
            int temp = parseExpression();
            if (current.getLexemeType() == CLOSE_BRACE) {
                current = lexer.getLexeme();
                return temp;
            } else {
                throw new ParserException();
            }
        }
        throw new ParserException();
    }
}
