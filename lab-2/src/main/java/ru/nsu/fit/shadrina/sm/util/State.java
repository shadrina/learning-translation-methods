package ru.nsu.fit.shadrina.sm.util;

import java.util.ArrayList;
import java.util.List;

public class State {
    private boolean finite;
    private int number;
    private List<TransitionFunction> transitionFunctions;

    public State(int number, boolean finite) {
        this.number = number;
        this.finite = finite;
        this.transitionFunctions = new ArrayList<>();
    }

    public State(int number) {
        this(number, false);
    }

    public void addTransitionFunction(TransitionFunction transitionFunction) {
        transitionFunctions.add(transitionFunction);
    }

    public boolean isFinite() {
        return finite;
    }

    public int getNumber() {
        return number;
    }

    public List<TransitionFunction> getTransitionFunctions() {
        return transitionFunctions;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
