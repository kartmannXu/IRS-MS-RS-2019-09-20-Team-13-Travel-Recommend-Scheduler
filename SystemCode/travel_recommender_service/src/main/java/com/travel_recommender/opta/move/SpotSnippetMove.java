package com.travel_recommender.opta.move;

import com.travel_recommender.opta.Solution;
import com.travel_recommender.opta.SpotSnippet;
import com.travel_recommender.opta.TimeCapsule;
import org.optaplanner.core.impl.heuristic.move.AbstractMove;
import org.optaplanner.core.impl.heuristic.move.Move;
import org.optaplanner.core.impl.score.director.ScoreDirector;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class SpotSnippetMove extends AbstractMove<Solution> {
    private SpotSnippet spotSnippet;
    private TimeCapsule toCapsule;


    public SpotSnippetMove(SpotSnippet spotSnippet, TimeCapsule toCapsule) {
        this.spotSnippet = spotSnippet;
        this.toCapsule = toCapsule;
    }

    @Override
    protected void doMoveOnGenuineVariables(ScoreDirector<Solution> scoreDirector) {
        scoreDirector.beforeVariableChanged(spotSnippet, "timeCapsule");
        spotSnippet.setTimeCapsule(toCapsule);
        scoreDirector.afterVariableChanged(spotSnippet, "timeCapsule");
    }

    @Override
    public boolean isMoveDoable(ScoreDirector<Solution> scoreDirector) {
        return !Objects.equals(spotSnippet.getTimeCapsule(), toCapsule);
    }

    @Override
    public Move<Solution> rebase(ScoreDirector<Solution> destinationScoreDirector) {
        return new SpotSnippetMove(destinationScoreDirector.lookUpWorkingObject(spotSnippet),
                destinationScoreDirector.lookUpWorkingObject(toCapsule));
    }

    @Override
    public String getSimpleMoveTypeDescription() {
        return "SpotSnippetMove(SpotSnippet.timeCapsule)";
    }

    @Override
    public Collection<? extends Object> getPlanningEntities() {
        return Collections.singletonList(spotSnippet);
    }

    @Override
    public Collection<? extends Object> getPlanningValues() {
        return Collections.singletonList(toCapsule);
    }

    @Override
    public String toString() {
        return spotSnippet + "{" + spotSnippet.getTimeCapsule() + " -> " + toCapsule + "}";
    }

    @Override
    public SpotSnippetMove createUndoMove(ScoreDirector<Solution> scoreDirector) {
        return new SpotSnippetMove(spotSnippet, spotSnippet.getTimeCapsule());
    }
}
