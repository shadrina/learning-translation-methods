package sm;

import lombok.AllArgsConstructor;
import picocli.CommandLine;
import sm.util.State;
import sm.util.TransitionFunction;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class NondeterministicFiniteStateMachine {
    @AllArgsConstructor
    private static class Configuration {
        State currentState;
        int nextPosition;
    }

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

        var configurationStack = new ArrayDeque<Configuration>();
        var initState = states.stream()
                .filter(state -> state.getNumber() == 1)
                .findFirst()
                .orElseThrow();
        configurationStack.push(new Configuration(initState, 0));
        while (!configurationStack.isEmpty()) {
            var currentConfig = configurationStack.pop();
            if (currentConfig.nextPosition == stringToParse.length) {
                if (currentConfig.currentState.isFinite()) return true;
                else continue;
            }
            var currentSymbol = stringToParse[currentConfig.nextPosition];
            var tfs = currentConfig.currentState.getTransitionFunctions().stream()
                    .filter(tf -> tf.getTransitionSymbol() == currentSymbol)
                    .collect(Collectors.toList());
            for (var tf: tfs) {
                configurationStack.push(new Configuration(tf.makeTransition(), currentConfig.nextPosition + 1));
            }
        }

        return false;
    }

    private void readMachineConfiguration() {
        try (var br = new BufferedReader(new FileReader(machineDescription))) {
            var maxNumber = Integer.parseInt(br.readLine());

            Arrays.stream(br.readLine()
                    .split(" "))
                    .map(Integer::parseInt)
                    .forEach(finiteStateNumber -> {
                        State st = new State(finiteStateNumber, true);
                        states.add(st);
                    });

            for (int i = 1; i <= maxNumber; ++i) {
                final int stateNumber = i;
                if (states.stream().noneMatch(state -> state.getNumber() == stateNumber)) {
                    states.add(new State(stateNumber));
                }
            }

            String line;
            while ((line = br.readLine()) != null) {
                var splitted = line.split(" ");
                var fromNumber = Integer.parseInt(splitted[0]);
                var transitionSymbol = splitted[1].toCharArray()[0];
                var toNumber = Integer.parseInt(splitted[2]);

                var from = states.stream()
                        .filter(state -> state.getNumber() == fromNumber)
                        .findFirst()
                        .orElseThrow();
                var to = states.stream()
                        .filter(state -> state.getNumber() == toNumber)
                        .findFirst()
                        .orElseThrow();

                var tf = new TransitionFunction(from, to, transitionSymbol);
                from.addTransitionFunction(tf);
            }

        } catch (IOException e) {
            // TODO: logging
            e.printStackTrace();
        }
    }

    private void readTargetFile() {
        try (var br = new BufferedReader(new FileReader(fileToParse))) {
            stringToParse = br.readLine().toCharArray();
        } catch (IOException e) {
            // TODO: logging
            e.printStackTrace();
        }
    }

}