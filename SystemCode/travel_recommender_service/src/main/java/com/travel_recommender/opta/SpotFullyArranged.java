package com.travel_recommender.opta;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Objects;

public class SpotFullyArranged implements Comparable<SpotFullyArranged>, Serializable {
    private Spot spot;
    private Day day;

    public SpotFullyArranged(Spot spot, Day day) {
        this.spot = spot;
        this.day = day;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "SpotFullyArranged{" +
                "spot=" + spot +
                ", day=" + day +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpotFullyArranged)) return false;
        SpotFullyArranged that = (SpotFullyArranged) o;
        return Objects.equals(spot, that.spot) &&
                Objects.equals(day, that.day);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spot, day);
    }

    @Override
    public int compareTo(@NotNull SpotFullyArranged o) {
        return new CompareToBuilder()
                .append(spot, o.spot)
                .append(day, o.day).toComparison();
    }
}
