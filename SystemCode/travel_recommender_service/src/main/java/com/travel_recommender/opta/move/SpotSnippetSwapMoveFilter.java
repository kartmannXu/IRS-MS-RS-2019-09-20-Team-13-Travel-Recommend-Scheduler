package com.travel_recommender.opta.move;

import com.travel_recommender.opta.Day;
import com.travel_recommender.opta.Solution;
import com.travel_recommender.opta.SpotSnippet;
import org.optaplanner.core.impl.heuristic.selector.common.decorator.SelectionFilter;
import org.optaplanner.core.impl.heuristic.selector.move.generic.SwapMove;
import org.optaplanner.core.impl.score.director.ScoreDirector;

import java.util.List;
import java.util.Objects;

public class SpotSnippetSwapMoveFilter implements SelectionFilter<Solution, SwapMove> {

    @Override
    public boolean accept(ScoreDirector<Solution> scoreDirector, SwapMove move) {
        SpotSnippet leftSnippet = (SpotSnippet) move.getLeftEntity();
        SpotSnippet rightSnippet = (SpotSnippet) move.getRightEntity();
        boolean sameSpot = leftSnippet.getSpot().getSpotId() == rightSnippet.getSpot().getSpotId();
        boolean neighbourSnippet = leftSnippet.getSnippetIndexInSpot() - rightSnippet.getSnippetIndexInSpot() == 1;
        int minTravelCapsuleNum = 1;
        boolean hasTimeForTravel = leftSnippet.getTimeCapsule().getId() - rightSnippet.getTimeCapsule().getId() >= minTravelCapsuleNum;

        return (sameSpot && neighbourSnippet) || (!sameSpot && hasTimeForTravel);
    }
}
