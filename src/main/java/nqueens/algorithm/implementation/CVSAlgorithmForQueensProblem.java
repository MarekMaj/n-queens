package nqueens.algorithm.implementation;

import nqueens.algorithm.api.ColumnChoiceHeuristic;
import nqueens.algorithm.api.QueensProblemAlgorithm;
import nqueens.domain.Board;

public class CVSAlgorithmForQueensProblem implements QueensProblemAlgorithm {
    private Board board;
    private int MAX = Integer.MAX_VALUE; 		//auxiliary variable corresponding an infinite number

    private ColumnChoiceHeuristic columnChoiceHeuristic;

    private long calculationTime;
    private int recursionSteps;

    public CVSAlgorithmForQueensProblem(Board board){
        this.board = board;
        this.columnChoiceHeuristic = new BruteForce();
    }

    public CVSAlgorithmForQueensProblem(Board board, ColumnChoiceHeuristic columnChoiceHeuristic){
        this.board = board;
        this.columnChoiceHeuristic = columnChoiceHeuristic;
    }

    @Override
    public boolean run(){
        long start = System.nanoTime();
        recursionSteps = 0;
        boolean res = go();
        calculationTime = System.nanoTime() - start;

        return res;
    }

    @Override
    public long getCalculationTimeInMilliseconds() {
        return calculationTime /1000000;
    }

    @Override
    public int getNumberOfRecursionSteps() {
        return recursionSteps;
    }

    public void showBoard(){
        board.showBoard();
    }

    public void showStats(){
        System.out.println("Time of calculations: " + String.valueOf(getCalculationTimeInMilliseconds()) + " [ms]");
        System.out.println("Recursion steps:      " + String.valueOf(getNumberOfRecursionSteps()));
    }

    private int getEmptyColumn(){
        return this.columnChoiceHeuristic.chooseColumn(board);
    }

    private boolean go(){
        if(board.isSolved())
            return true;

        ++recursionSteps;

        int nextCol = getEmptyColumn();
        if(nextCol == MAX)
            return false;

        for(int i=0; i<board.getSize(); ++i){
            if(board.isQueenAllowed(nextCol, i)){
                board.placeQueen(nextCol, i);
                if(!go())
                    board.removeQueen(nextCol, i);
                else
                    return true;
            }
        }

        return false;	//This branch can not be solved
    }
}
