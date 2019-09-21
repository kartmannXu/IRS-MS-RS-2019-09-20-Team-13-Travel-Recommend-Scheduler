package com.travel_recommender.opta;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Objects;

public class SpotConsecutiveEnd implements Serializable, Comparable<SpotConsecutiveEnd> {

    private Spot spot;
    private TimeCapsule timeCapsule;

    public SpotConsecutiveEnd(Spot spot, TimeCapsule timeCapsule) {
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
        if (!(o instanceof SpotConsecutiveEnd)) return false;
        SpotConsecutiveEnd that = (SpotConsecutiveEnd) o;
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
        return "SnippetConsecutiveEnd{" +
                "spot=" + spot +
                ", timeCapsule=" + timeCapsule +
                '}';
    }

    @Override
    public int compareTo(@NotNull SpotConsecutiveEnd o) {
        return new CompareToBuilder()
                .append(spot, o.spot)
                .append(timeCapsule, o.timeCapsule).toComparison();
    }

}
