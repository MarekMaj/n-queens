public class Algorithm {
    private Board board;
    private int N;                            //size of the problem, board NxN
    private int M;                             //auxiliary variable corresponding an infinite number

    private boolean withHeuristic;            //determines if heuristic for selecting variables should be used

    private long time;                        //time of calculations
    private int cntr;                        //no. of recursion steps

    public Algorithm(int n, boolean useHeuristic) {
        board = new Board(n);
        N = n;
        M = N * N;
        withHeuristic = useHeuristic;
    }

    /**
     * Beware: 	if you want to run this method multiple times on the same object
     * you should construct new board each time
     *
     * @return true if solution was found, false otherwise
     */
    public boolean run() {
        long start = System.nanoTime();
        cntr = 0;
        boolean res = go();
        time = System.nanoTime() - start;

        return res;
    }

    public void showBoard() {
        board.showBoard();
    }

    public void showStats() {
        System.out.println("Time of calculations: " + String.valueOf(time / 1000000) + " [ms]");
        System.out.println("Recursion steps:      " + String.valueOf(cntr));
    }

    private int getEmptyColumn() {
        if (withHeuristic) {        //MRV - Minimum Remaining Values
            int min = M;
            int res = M;
            for (int i = 0; i < N; ++i) {
                int freeRows = board.noOfFreeRows(i);
                if (freeRows < min && freeRows > 0) {
                    min = freeRows;
                    res = i;
                }
            }
            return res;
        } else {                    //BruteForce version
            for (int i = 0; i < N; ++i)
                if (board.isColumnEmpty(i))
                    return i;
            return M;
        }
    }

    private boolean go() {
        ++cntr;

        if (board.isSolved())
            return true;

        int nextCol = getEmptyColumn();
        if (nextCol == M)
            return false;

        for (int i = 0; i < N; ++i) {
            if (board.isQueenAllowed(nextCol, i)) {
                board.placeQueen(nextCol, i);
                if (!go())
                    board.removeQueen(nextCol, i);
                else
                    return true;
            }
        }

        return false;    //This branch can not be solved
    }
}
