package ru.nsu.fit.shadrina.lexer;

import org.junit.Test;

import java.io.StringReader;

import static org.junit.Assert.*;

public class LexerTest {

    @Test
    public void getPlusLexeme() {
        Throwable caught = null;
        String plusLexeme = "+";
        LexemeType t = LexemeType.EMPTY;
        try {
            t = new Lexer(new StringReader(plusLexeme)).getLexeme().getLexemeType();
        } catch (Exception e) {
            caught = e;
        }
        assertNull(caught);
        assertEquals(t, LexemeType.PLUS);
    }

    @Test
    public void getMinusLexeme() {
        Throwable caught = null;
        String plusLexeme = "-";
        LexemeType t = LexemeType.EMPTY;
        try {
            t = new Lexer(new StringReader(plusLexeme)).getLexeme().getLexemeType();
        } catch (Exception e) {
            caught = e;
        }
        assertNull(caught);
        assertEquals(t, LexemeType.MINUS);
    }

    @Test
    public void getMultLexeme() {
        Throwable caught = null;
        String plusLexeme = "*";
        LexemeType t = LexemeType.EMPTY;
        try {
            t = new Lexer(new StringReader(plusLexeme)).getLexeme().getLexemeType();
        } catch (Exception e) {
            caught = e;
        }
        assertNull(caught);
        assertEquals(t, LexemeType.MULT);
    }

    @Test
    public void getDivLexeme() {
        Throwable caught = null;
        String plusLexeme = "/";
        LexemeType t = LexemeType.EMPTY;
        try {
            t = new Lexer(new StringReader(plusLexeme)).getLexeme().getLexemeType();
        } catch (Exception e) {
            caught = e;
        }
        assertNull(caught);
        assertEquals(t, LexemeType.DIV);
    }

    @Test
    public void getPowerLexeme() {
        Throwable caught = null;
        String plusLexeme = "^";
        LexemeType t = LexemeType.EMPTY;
        try {
            t = new Lexer(new StringReader(plusLexeme)).getLexeme().getLexemeType();
        } catch (Exception e) {
            caught = e;
        }
        assertNull(caught);
        assertEquals(t, LexemeType.POWER);
    }

    @Test
    public void getOpenBraceLexeme() {
        Throwable caught = null;
        String plusLexeme = "(";
        LexemeType t = LexemeType.EMPTY;
        try {
            t = new Lexer(new StringReader(plusLexeme)).getLexeme().getLexemeType();
        } catch (Exception e) {
            caught = e;
        }
        assertNull(caught);
        assertEquals(t, LexemeType.OPEN_BRACE);
    }

    @Test
    public void getCloseBraceLexeme() {
        Throwable caught = null;
        String plusLexeme = ")";
        LexemeType t = LexemeType.EMPTY;
        try {
            t = new Lexer(new StringReader(plusLexeme)).getLexeme().getLexemeType();
        } catch (Exception e) {
            caught = e;
        }
        assertNull(caught);
        assertEquals(t, LexemeType.CLOSE_BRACE);
    }

    @Test
    public void getShortNumberLexeme() {
        Throwable caught = null;
        String plusLexeme = "3";
        LexemeType t = LexemeType.EMPTY;
        try {
            t = new Lexer(new StringReader(plusLexeme)).getLexeme().getLexemeType();
        } catch (Exception e) {
            caught = e;
        }
        assertNull(caught);
        assertEquals(t, LexemeType.NUMBER);
    }

    @Test
    public void getLongNumberLexeme() {
        Throwable caught = null;
        String plusLexeme = "3123445664534";
        LexemeType t = LexemeType.EMPTY;
        try {
            t = new Lexer(new StringReader(plusLexeme)).getLexeme().getLexemeType();
        } catch (Exception e) {
            caught = e;
        }
        assertNull(caught);
        assertEquals(t, LexemeType.NUMBER);
    }

    @Test
    public void getErroneousLexeme() {
        Throwable caught = null;
        String plusLexeme = "[";
        try {
            new Lexer(new StringReader(plusLexeme)).getLexeme();
        } catch (Exception e) {
            caught = e;
        }
        assertNotNull(caught);
    }
}