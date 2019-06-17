package sm.util;

import lombok.Getter;
import lombok.Setter;

public class TransitionFunction {
    @Getter @Setter
    private State from;
    @Getter @Setter
    private State to;
    @Getter @Setter
    private char transitionSymbol;

    public TransitionFunction(State from, State to, char transitionSymbol) {
        this.from = from;
        this.to = to;
        this.transitionSymbol = transitionSymbol;
    }

    public State makeTransition() {
        return to;
    }
}

