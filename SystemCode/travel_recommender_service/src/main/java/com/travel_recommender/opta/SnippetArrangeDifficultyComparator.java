package com.travel_recommender.opta;

import org.apache.commons.lang3.builder.CompareToBuilder;

import java.io.Serializable;
import java.util.Comparator;

public class SnippetArrangeDifficultyComparator implements Comparator<SpotSnippet>, Serializable {

    @Override
    public int compare(SpotSnippet a, SpotSnippet b) {
        Spot aspot = a.getSpot();
        Spot bspot = b.getSpot();
        return new CompareToBuilder()
                .append(aspot.getEst_duration(), bspot.getEst_duration())
                .append(aspot.getScore(), bspot.getScore()).toComparison();
    }
}
