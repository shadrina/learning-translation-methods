package ru.nsu.fit.shadrina.parser;

import ru.nsu.fit.shadrina.exceptions.ParserException;
import ru.nsu.fit.shadrina.exceptions.ParserExceptionType;
import ru.nsu.fit.shadrina.lexer.Lexer;

import java.io.StringReader;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParserTest {

    @Test
    public void parseSimpleExpression() {
        Throwable caught = null;
        String test = "1 + 2 + 3";
        int expectedResult = 6;
        int result = 0;
        Parser parser = new Parser(new Lexer(new StringReader(test)));
        try {
            result = parser.parse();
        } catch (Exception e) {
            caught = e;
        }
        assertNull(caught);
        assertEquals(expectedResult, result);
    }

    @Test
    public void parseComplexExpression() {
        Throwable caught = null;
        String test = "(3 + 7863) / 3 * 45 - 1000 +11111    ^ 1 + (2)";
        int expectedResult = 128103;
        int result = 0;
        Parser parser = new Parser(new Lexer(new StringReader(test)));
        try {
            result = parser.parse();
        } catch (Exception e) {
            caught = e;
        }
        assertNull(caught);
        assertEquals(expectedResult, result);
    }

    @Test
    public void parseWithNoEof() {
        Throwable caught = null;
        String test = "1 + 3 + 3)";
        Parser parser = new Parser(new Lexer(new StringReader(test)));
        try {
            parser.parse();
        } catch (Exception e) {
            caught = e;
        }
        assertNotNull(caught);
        assertTrue(caught instanceof ParserException
                && ((ParserException) caught).getType() == ParserExceptionType.NO_EOF);
    }

    @Test
    public void parseWithNoClosingBracket() {
        Throwable caught = null;
        String test = "(1 + 3 + 3";
        Parser parser = new Parser(new Lexer(new StringReader(test)));
        try {
            parser.parse();
        } catch (Exception e) {
            caught = e;
        }
        assertNotNull(caught);
        assertTrue(caught instanceof ParserException
                && ((ParserException) caught).getType() == ParserExceptionType.NO_CLOSING_BRACKET);
    }

    @Test
    public void parseWithUnknownAtom() {
        Throwable caught = null;
        String test = "1 + 3 + 3++";
        Parser parser = new Parser(new Lexer(new StringReader(test)));
        try {
            parser.parse();
        } catch (Exception e) {
            caught = e;
        }
        assertNotNull(caught);
        assertTrue(caught instanceof ParserException
                && ((ParserException) caught).getType() == ParserExceptionType.UNKNOWN_ATOM);
    }
}