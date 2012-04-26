package nqueens.factory;

import nqueens.algorithm.api.ColumnChoiceHeuristic;
import nqueens.algorithm.implementation.BruteForce;
import nqueens.algorithm.implementation.MinimumRemainingValues;

public class ColumnChoiceHeuristicFactory {

    public static ColumnChoiceHeuristic create(HeuristicEnum heuristicEnum){
        if (heuristicEnum.equals(HeuristicEnum.BruteForce)){
            return new BruteForce();
        }
        else if (heuristicEnum.equals(HeuristicEnum.MinimumRemainingValues)){
            return new MinimumRemainingValues();
        }

        return null;
    }
}
