package nqueens.algorithm.api;

import nqueens.domain.Board;

public interface QueensProblemAlgorithm {

    /**
     * Runs algorithm. Returns true if solution was found
     *
     * @return true if solution was found, false otherwise
     */
    public boolean run();
    public long getCalculationTimeInMilliseconds();
    public int getNumberOfRecursionSteps();
    public Board getBoard();
}
