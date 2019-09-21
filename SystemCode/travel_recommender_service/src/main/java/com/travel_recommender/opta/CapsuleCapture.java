package com.travel_recommender.opta;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.InverseRelationShadowVariable;

@PlanningEntity
public interface CapsuleCapture {
    TimeCapsule getTimeCapsule();

    @InverseRelationShadowVariable(sourceVariableName = "preciousTimeCapsule")
    SpotSnippet getNextSpotSnippet();
    void setNextSpotSnippet(SpotSnippet nextSpotSnippet);
}
