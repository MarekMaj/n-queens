package nqueens.factory;

import nqueens.algorithm.api.QueensProblemAlgorithm;
import nqueens.algorithm.implementation.CPSAlgorithmForQueensProblem;
import nqueens.domain.Board;

public class QueensProblemAlgorithmFactory {

    public static QueensProblemAlgorithm create(int boardSize, AlgorithmEnum algorithmEnum, HeuristicEnum heuristicEnum){
        if (algorithmEnum.equals(AlgorithmEnum.CPSAlgorithm)){
            return new CPSAlgorithmForQueensProblem(new Board(boardSize), ColumnChoiceHeuristicFactory.create(heuristicEnum));
        }
        return null;
    }
}
