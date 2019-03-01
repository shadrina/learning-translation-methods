package ru.nsu.fit.shadrina.sm.util;

public class TransitionFunction {
    private State from;
    private State to;
    private char transitionSymbol;

    public TransitionFunction(State from, State to, char transitionSymbol) {
        this.from = from;
        this.to = to;
        this.transitionSymbol = transitionSymbol;
    }

    public State makeTransition() {
        return to;
    }

    public State getFrom() {
        return from;
    }

    public State getTo() {
        return to;
    }

    public char getTransitionSymbol() {
        return transitionSymbol;
    }

    public void setFrom(State from) {
        this.from = from;
    }

    public void setTo(State to) {
        this.to = to;
    }

    public void setTransitionSymbol(char transitionSymbol) {
        this.transitionSymbol = transitionSymbol;
    }
}
