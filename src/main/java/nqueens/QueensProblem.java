package nqueens;

import nqueens.algorithm.implementation.BruteForce;
import nqueens.algorithm.implementation.CVSAlgorithmForQueensProblem;
import nqueens.algorithm.implementation.MinimumRemainingValues;
import nqueens.domain.Board;

public class QueensProblem {

    public static void main(String[] args) {

        //CVSAlgorithmForQueensProblem a = new CVSAlgorithmForQueensProblem(new Board(12), new BruteForce());
        CVSAlgorithmForQueensProblem a = new CVSAlgorithmForQueensProblem(new Board(30), new MinimumRemainingValues());
        a.run();
        //if run returns false it means that there is no solution e.g. n=3

        a.showStats();
        a.showBoard();
    }

}


