package com.travel_recommender.opta;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Objects;

public class SpotConsecutiveStart implements Serializable, Comparable<SpotConsecutiveStart> {

    private Spot spot;
    private TimeCapsule timeCapsule;

    public SpotConsecutiveStart(Spot spot, TimeCapsule timeCapsule) {
        this.spot = spot;
        this.timeCapsule = timeCapsule;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    public TimeCapsule getTimeCapsule() {
        return timeCapsule;
    }

    public void setTimeCapsule(TimeCapsule timeCapsule) {
        this.timeCapsule = timeCapsule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpotConsecutiveStart)) return false;
        SpotConsecutiveStart that = (SpotConsecutiveStart) o;
        return new EqualsBuilder()
                .append(spot, that.spot)
                .append(timeCapsule, that.timeCapsule)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return Objects.hash(spot, timeCapsule);
    }

    @Override
    public String toString() {
        return "SnippetConsecutiveStart{" +
                "spot=" + spot +
                ", timeCapsule=" + timeCapsule +
                '}';
    }

    @Override
    public int compareTo(@NotNull SpotConsecutiveStart o) {
        return new CompareToBuilder()
                .append(spot, o.spot)
                .append(timeCapsule, o.timeCapsule).toComparison();
    }

}
