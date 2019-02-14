package ru.nsu.fit.shadrina;

import ru.nsu.fit.shadrina.lexer.Lexer;
import ru.nsu.fit.shadrina.parser.Parser;

import java.io.StringReader;

public class Main {
    public static void main(String[] args) {
        String test = "((45) - 78)^(1 + 2 + 3)";
        Parser parser = new Parser(new Lexer(new StringReader(test)));
        try {
            System.out.print(parser.parse());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
