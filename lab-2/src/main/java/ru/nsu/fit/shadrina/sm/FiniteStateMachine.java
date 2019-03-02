package ru.nsu.fit.shadrina.sm;

import org.apache.log4j.Logger;
import picocli.CommandLine;
import ru.nsu.fit.shadrina.sm.util.State;
import ru.nsu.fit.shadrina.sm.util.TransitionFunction;

import java.io.*;
import java.util.*;

public class FiniteStateMachine {
    private static final Logger log = Logger.getLogger(FiniteStateMachine.class);

    @CommandLine.Option(names = { "-d", "--desc" }, paramLabel = "FILE", required = true,
            description = "machine description")
    private File machineDescription;
    @CommandLine.Option(names = { "-t", "--target" }, paramLabel = "FILE", required = true,
            description = "file with string to parse")
    private File fileToParse;

    private List<State> states = new ArrayList<>();
    private char[] stringToParse;

    public boolean parse() {
        readMachineConfiguration();
        readTargetFile();
        State currentState = states.stream().filter(state -> state.getNumber() == 1).findFirst().get();;
        for (char symbol: stringToParse) {
            List<TransitionFunction> tfs = currentState.getTransitionFunctions();
            TransitionFunction tf = tfs.stream().filter(f -> f.getTransitionSymbol() == symbol).findFirst().get();
            currentState = tf.makeTransition();
        }
        return currentState.isFinite();
    }

    private void readMachineConfiguration() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(machineDescription));

            int maxNumber = Integer.parseInt(br.readLine());
            log.info("Read finite states number " + maxNumber);
            Arrays.stream(br.readLine()
                    .split(" "))
                    .map(Integer::parseInt)
                    .forEach(finiteStateNumber -> {
                        State st = new State(finiteStateNumber, true);
                        states.add(st);
            });
            log.info("Read finite states");

            for (int i = 1; i <= maxNumber; ++i) {
                final int stateNumber = i;
                if (states.stream().noneMatch(state -> state.getNumber() == stateNumber)) {
                    states.add(new State(stateNumber));
                }
            }

            String line;
            while ((line = br.readLine()) != null) {
                String[] splitted = line.split(" ");
                int fromNumber = Integer.parseInt(splitted[0]);
                char transitionSymbol = splitted[1].toCharArray()[0];
                int toNumber = Integer.parseInt(splitted[2]);

                State from = states.stream().filter(state -> state.getNumber() == fromNumber).findFirst().get();
                State to = states.stream().filter(state -> state.getNumber() == toNumber).findFirst().get();

                TransitionFunction tf = new TransitionFunction(from, to, transitionSymbol);
                from.addTransitionFunction(tf);
            }
            log.info("Read transition functions");

        } catch (FileNotFoundException e) {
            log.error("Machine description file cannot be found");
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void readTargetFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileToParse));
            stringToParse = br.readLine().toCharArray();
        } catch (FileNotFoundException e) {
            log.error("Target string file cannot be found");
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

}
