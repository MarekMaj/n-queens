package nqueens.command;

public class FinishCommand implements Command{

    @Override
    public void execute() {
        System.exit(0);
    }
}
