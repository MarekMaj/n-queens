package nqueens.algorithm.implementation;

import nqueens.algorithm.api.ColumnChoiceHeuristic;
import nqueens.domain.Board;

public class BruteForce implements ColumnChoiceHeuristic{
    /* Brute force heuristic choosing first free column. Can be very slow  */

    @Override
    public int chooseColumn(Board board) {
        for(int i=0; i<board.getSize(); ++i)
            if(board.isColumnEmpty(i))
                return i;

        return Integer.MAX_VALUE;
    }
}
