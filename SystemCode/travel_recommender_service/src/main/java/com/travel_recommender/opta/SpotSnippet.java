package com.travel_recommender.opta;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity(difficultyComparatorClass = SnippetArrangeDifficultyComparator.class)
public class SpotSnippet extends AbstractPersistable{

    private int snippetIndexInSpot;
    private Spot spot;

    private TimeCapsule timeCapsule;

    public int getSnippetIndexInSpot() {
        return snippetIndexInSpot;
    }

    public void setSnippetIndexInSpot(int snippetIndexInSpot) {
        this.snippetIndexInSpot = snippetIndexInSpot;
    }

    @PlanningVariable(valueRangeProviderRefs = {"timeRange"},
            strengthComparatorClass = TimeCapsuleStrengthComparator.class)
    public TimeCapsule getTimeCapsule() {
        return timeCapsule;
    }

    public void setTimeCapsule(TimeCapsule timeCapsule) {
        this.timeCapsule = timeCapsule;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    public SpotSnippet(Long id, int snippetIndexInSpot, Spot spot, TimeCapsule timeCapsule, Day day) {
        super(id);
        this.snippetIndexInSpot = snippetIndexInSpot;
        this.spot = spot;
        this.timeCapsule = timeCapsule;
    }

    public SpotSnippet() {
        super();
    }

    public float getScore() {
        return (timeCapsule != null) ? timeCapsule.getScore() : 0;
    }

    @Override
    public String toString() {
        return "SpotSnippet{" +
                "snippetIndexInSpot=" + snippetIndexInSpot +
                ", spot=" + spot +
                ", timeCapsule=" + timeCapsule +
                ", id=" + id +
                '}';
    }
}
