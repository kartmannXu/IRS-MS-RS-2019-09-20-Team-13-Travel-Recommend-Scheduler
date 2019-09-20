package com.travel_recommender.opta;

import org.apache.commons.lang3.builder.CompareToBuilder;

import java.util.Comparator;

public class TimeCapsuleStrengthComparator implements Comparator<TimeCapsule> {
    @Override
    public int compare(TimeCapsule a, TimeCapsule b) {
        return new CompareToBuilder()
                .append(a.getScore(), b.getScore()).toComparison();
    }
}
