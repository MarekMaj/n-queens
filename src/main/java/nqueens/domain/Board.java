package nqueens.domain;

public class Board {

    private int size;                    //size of the problem, board NxN
    private int[] cols;
    private int[] rows;
    private int queensPlacedOnTheBoard = 0;         //queens currently placed on the board

    public Board(int size) {
        this.size = size;
        cols = new int[size];
        rows = new int[size];

        for (int i = 0; i < size; ++i) {
            cols[i] = size;
            rows[i] = size;
        }
    }

    public int getSize() {
        return size;
    }

    public boolean isSolved() {
        return queensPlacedOnTheBoard == size;
    }

    public void placeQueen(int col, int row) {
        cols[col] = row;
        rows[row] = col;
        ++queensPlacedOnTheBoard;
    }

    public void removeQueen(int col, int row) {
        cols[col] = size;
        rows[row] = size;
        --queensPlacedOnTheBoard;
    }


    public boolean isQueenAllowed(int col, int row) {
        if (isFieldDisallowed(col, row)) return false;

        for (int rad = 1; rad < size; ++rad) {
            if (isCellContainQueen(col - rad, row - rad) ||
                    isCellContainQueen(col - rad, row + rad) ||
                    isCellContainQueen(col + rad, row - rad) ||
                    isCellContainQueen(col + rad, row + rad))
                return false;
        }
        return true;
    }

    private boolean isFieldDisallowed(int col, int row) {
        if (col >= size || col < 0 || row >= size || row < 0 || !isColumnEmpty(col) || !isRowEmpty(row))
            return true;
        return false;
    }

    /**
     * @param col - column index
     * @return number of rows for selected column where queen placing is allowed
     */
    public int noOfFreeRows(int col) {
        int res = 0;
        for (int i = 0; i < size; ++i)
            if (isQueenAllowed(col, i))
                ++res;
        return res;
    }

    public boolean isColumnEmpty(int col) {
        return cols[col] == size;
    }

    private boolean isRowEmpty(int row) {
        return rows[row] == size;
    }

    public boolean isCellContainQueen(int col, int row) {
        if (col < size && col > -1 && row < size && row > -1 && cols[col] == row)
            return true;
        return false;
    }
}
