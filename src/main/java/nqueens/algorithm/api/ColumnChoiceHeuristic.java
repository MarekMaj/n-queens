package nqueens.algorithm.api;

import nqueens.domain.Board;

public interface ColumnChoiceHeuristic {

    /**
     * Chooses column number for queen to be placed in one step of algorithm.
     *
     * @return number of column choosen
     */
    public int chooseColumn(Board board);
}
