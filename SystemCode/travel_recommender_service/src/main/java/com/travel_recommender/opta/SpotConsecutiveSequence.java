package com.travel_recommender.opta;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Objects;

public class SpotConsecutiveSequence implements Comparable<SpotConsecutiveSequence>, Serializable {

    private Spot spot;
    private TimeCapsule startCapsule;
    private TimeCapsule endCapsule;

    public SpotConsecutiveSequence(Spot spot, TimeCapsule startCapsule, TimeCapsule endCapsule) {
        this.spot = spot;
        this.startCapsule = startCapsule;
        this.endCapsule = endCapsule;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    public TimeCapsule getStartCapsule() {
        return startCapsule;
    }

    public void setStartCapsule(TimeCapsule startCapsule) {
        this.startCapsule = startCapsule;
    }

    public TimeCapsule getEndCapsule() {
        return endCapsule;
    }

    public void setEndCapsule(TimeCapsule endCapsule) {
        this.endCapsule = endCapsule;
    }

    @Override
    public String toString() {
        return "SpotConsecutiveSequence{" +
                "spot=" + spot +
                ", startCapsule=" + startCapsule +
                ", endCapsule=" + endCapsule +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpotConsecutiveSequence)) return false;
        SpotConsecutiveSequence that = (SpotConsecutiveSequence) o;
        return Objects.equals(spot, that.spot) &&
                Objects.equals(startCapsule, that.startCapsule) &&
                Objects.equals(endCapsule, that.endCapsule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spot, startCapsule, endCapsule);
    }

    @Override
    public int compareTo(@NotNull SpotConsecutiveSequence o) {
        return new CompareToBuilder()
                .append(spot, o.spot)
                .append(endCapsule, o.endCapsule)
                .append(startCapsule, o.startCapsule).toComparison();
    }

    public long getStartCaspsuleId() {
        return startCapsule.getId();
    }

    public long getEndCapsuleId() {
        return endCapsule.getId();
    }
}
