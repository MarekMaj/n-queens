package nqueens.algorithm.implementation;

import nqueens.algorithm.api.ColumnChoiceHeuristic;
import nqueens.domain.Board;

public class MinimumRemainingValues implements ColumnChoiceHeuristic{
    /* The Minimum Remaining Values heuristic selects the variable with the least amount of possible
     * values. By doing so, the branching factor of the tree is greatly reduced and, effectively,
     * the time required to find a solution decreases.
     */

    @Override
    public int chooseColumn(Board board) {
        int min = Integer.MAX_VALUE;
        int res = Integer.MAX_VALUE;
        for(int i=0; i<board.getSize(); ++i){
            int freeRows = board.noOfFreeRows(i);
            if(freeRows < min && freeRows > 0){
                min = freeRows;
                res = i;
            }
        }
        return res;
    }
}
