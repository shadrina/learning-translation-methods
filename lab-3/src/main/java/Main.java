import picocli.CommandLine;
import sm.NondeterministicFiniteStateMachine;

public class Main {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";

    public static void main(String[] args) {
        var sm = CommandLine.populateCommand(new NondeterministicFiniteStateMachine(), args);
        System.out.println("Input string " +
                (sm.parse()
                        ? ANSI_GREEN + "can" + ANSI_RESET
                        : ANSI_RED + "cannot" + ANSI_RESET)
                + " be parsed");
    }
}
