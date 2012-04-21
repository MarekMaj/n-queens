public class Board {

    private int N;                    //size of the problem, board NxN
    private int[] cols;
    private int[] rows;
    private int noOfQueens;         //queens currently placed on the board

    public Board(int n) {
        N = n;
        cols = new int[N];
        rows = new int[N];

        for (int i = 0; i < N; ++i) {
            cols[i] = N;
            rows[i] = N;
        }

        noOfQueens = 0;
    }

    public void showBoard() {
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (cols[j] == i)
                    System.out.print("Q");
                else
                    System.out.print(".");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public boolean isSolved() {
        return noOfQueens == N;
    }

    public void placeQueen(int col, int row) {
        cols[col] = row;
        rows[row] = col;
        ++noOfQueens;
    }

    public void removeQueen(int col, int row) {
        cols[col] = N;
        rows[row] = N;
        --noOfQueens;
    }


    public boolean isQueenAllowed(int col, int row) {
        if (col >= N || col < 0 || row >= N || row < 0 || !isColumnEmpty(col) || !isRowEmpty(row))
            return false;

        for (int rad = 1; rad < N; ++rad) {
            if (doesCellContainQueen(col - rad, row - rad) ||
                    doesCellContainQueen(col - rad, row + rad) ||
                    doesCellContainQueen(col + rad, row - rad) ||
                    doesCellContainQueen(col + rad, row + rad))
                return false;
        }
        return true;
    }

    /**
     * @param col - column index
     * @return number of rows for selected column where queen placing is allowed
     */
    public int noOfFreeRows(int col) {
        int res = 0;
        for (int i = 0; i < N; ++i)
            if (isQueenAllowed(col, i))
                ++res;
        return res;
    }

    public boolean isColumnEmpty(int col) {
        return cols[col] == N;
    }

    private boolean isRowEmpty(int row) {
        return rows[row] == N;
    }

    private boolean doesCellContainQueen(int col, int row) {
        if (col < N && col > -1 && row < N && row > -1 && cols[col] == row)
            return true;
        return false;
    }
}
