public class QueensProblem {

    public static void main(String[] args) {

        Algorithm a = new Algorithm(30, true);
        a.run();
        //if run returns false it means that there is no solution e.g. n=3

        a.showStats();
        a.showBoard();
    }

}


