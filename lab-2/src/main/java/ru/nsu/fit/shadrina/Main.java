package ru.nsu.fit.shadrina;

import picocli.CommandLine;
import ru.nsu.fit.shadrina.sm.FiniteStateMachine;

public class Main {
    public static void main(String[] args) {
        FiniteStateMachine sm = CommandLine.populateCommand(new FiniteStateMachine(), args);
        System.out.println("Input string " + (sm.parse() ? "can" : "cannot") + " be parsed");
    }
}
