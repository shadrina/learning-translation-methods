package sm.util;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class State {
    @Getter
    private boolean finite;
    @Getter @Setter
    private int number;
    @Getter
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
}

