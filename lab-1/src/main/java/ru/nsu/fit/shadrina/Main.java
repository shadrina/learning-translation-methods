package ru.nsu.fit.shadrina;

import org.apache.log4j.Logger;
import ru.nsu.fit.shadrina.exceptions.LexerException;
import ru.nsu.fit.shadrina.exceptions.ParserException;
import ru.nsu.fit.shadrina.lexer.Lexer;
import ru.nsu.fit.shadrina.parser.Parser;

import java.io.IOException;
import java.io.StringReader;

public class Main {
    
    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        String test = "((45) - 78)^(1 + 2 + 3)";
        Parser parser = new Parser(new Lexer(new StringReader(test)));
        try {
            System.out.print(parser.parse());
        } catch (LexerException | ParserException e) {
            log.info(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
