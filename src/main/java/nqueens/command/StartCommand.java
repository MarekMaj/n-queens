package nqueens.command;

import nqueens.algorithm.api.QueensProblemAlgorithm;
import nqueens.factory.AlgorithmEnum;
import nqueens.factory.HeuristicEnum;
import nqueens.factory.QueensProblemAlgorithmFactory;
import nqueens.gui.BoardFrame;

import javax.swing.*;

public class StartCommand implements Command{

    private QueensProblemAlgorithm queensProblemAlgorithm;
    private boolean showBoard;
    private JLabel algorithmExecutionTimeField;
    private JLabel algorithmStepsField;

    public StartCommand(AlgorithmEnum algorithmEnum, HeuristicEnum heuristicEnum, int boardSize) {
        queensProblemAlgorithm = QueensProblemAlgorithmFactory.
                create(boardSize, algorithmEnum, heuristicEnum);
    }

    public StartCommand(AlgorithmEnum algorithmEnum, HeuristicEnum heuristicEnum, int boardSize,
                        JLabel algorithmExecutionTimeField, JLabel algorithmStepsField, boolean showBoard) {
        queensProblemAlgorithm = QueensProblemAlgorithmFactory.
                create(boardSize, algorithmEnum, heuristicEnum);

        this.algorithmExecutionTimeField = algorithmExecutionTimeField;
        this.algorithmStepsField = algorithmStepsField;
        this.showBoard = showBoard;
    }
    
    @Override
    public void execute() {
        boolean result = queensProblemAlgorithm.run();
        setAlgorithmExecutionTimeFieldIfNecessary(result);
        setAlgorithmStepsFieldIfNecessary(result);
        showBoardIfNecessary(result);
    }

    private void setAlgorithmExecutionTimeFieldIfNecessary(boolean result) {
        if (algorithmExecutionTimeField != null){
            algorithmExecutionTimeField.setText((result) ?
                    String.valueOf(queensProblemAlgorithm.getCalculationTimeInMilliseconds())
                    : "Nie znaleziono rozwiązania");
        }
    }

    private void setAlgorithmStepsFieldIfNecessary(boolean result) {
        if (algorithmStepsField != null){
            algorithmStepsField.setText((result) ?
                    String.valueOf(queensProblemAlgorithm.getNumberOfRecursionSteps())
                    : "Nie znaleziono rozwiązania");
        }
    }

    private void showBoardIfNecessary(boolean result){
        if (result && showBoard){
            new BoardFrame(queensProblemAlgorithm.getBoard());
        }
    }



    public StartCommand addShowBoard(boolean showBoard) {
        this.showBoard = showBoard;
        return this;
    }

    public StartCommand addAlgorithmExecutionTimeField(JLabel algorithmExecutionTimeField) {
        this.algorithmExecutionTimeField = algorithmExecutionTimeField;
        return this;
    }

    public StartCommand addAlgorithmStepsField(JLabel algorithmStepsField) {
        this.algorithmStepsField = algorithmStepsField;
        return this;
    }
}
