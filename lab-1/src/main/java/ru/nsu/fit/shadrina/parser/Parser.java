package ru.nsu.fit.shadrina.parser;

import ru.nsu.fit.shadrina.lexer.Lexeme;
import ru.nsu.fit.shadrina.lexer.LexemeType;
import ru.nsu.fit.shadrina.lexer.Lexer;

import static ru.nsu.fit.shadrina.lexer.LexemeType.*;

public class Parser {
    private Lexer lexer;
    private Lexeme current;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        try {
            this.current = lexer.getLexeme();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int parse() throws Exception {
        int result = parseExpression();
        if (lexer.getLexeme().getLexemeType() != EOF) {
            // TODO: throw new ParserException()
            throw new Exception();
        }
        return result;
    }

    private int parseExpression() throws Exception {
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

    private int parseTerm() throws Exception {
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

    private int parseFactor() throws Exception {
        int temp = parsePower();
        if (current.getLexemeType() == POWER) {
            current = lexer.getLexeme();
            temp = (int)Math.pow(temp, parseFactor());
        }
        return temp;
    }

    private int parsePower() throws Exception {
        if (current.getLexemeType() == MINUS) {
            current = lexer.getLexeme();
            return -parseAtom();
        }
        return parseAtom();
    }

    private int parseAtom() throws Exception {
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
                // TODO throw ParserException()
                throw new Exception();
            }
        }
        // TODO: throw new ParserException()
        throw new Exception();
    }
}
