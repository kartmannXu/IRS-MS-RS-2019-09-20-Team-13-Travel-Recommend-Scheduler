package com.travel_recommender.opta;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class TimeCapsuleAlreadyAssigned implements Comparable<TimeCapsuleAlreadyAssigned>, Serializable {

    private TimeCapsule timeCapsule;
    private SpotSnippet snippet;

    public TimeCapsuleAlreadyAssigned(TimeCapsule timeCapsule, SpotSnippet snippet) {
        this.timeCapsule = timeCapsule;
        this.snippet = snippet;
    }

    public TimeCapsule getTimeCapsule() {
        return timeCapsule;
    }

    public void setTimeCapsule(TimeCapsule timeCapsule) {
        this.timeCapsule = timeCapsule;
    }

    public SpotSnippet getSnippet() {
        return snippet;
    }

    public void setSnippet(SpotSnippet snippet) {
        this.snippet = snippet;
    }

    @Override
    public int compareTo(@NotNull TimeCapsuleAlreadyAssigned o) {
        return new CompareToBuilder()
                .append(timeCapsule, o.timeCapsule)
                .append(snippet, o.snippet)
                .toComparison();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof TimeCapsuleAlreadyAssigned) {
            TimeCapsuleAlreadyAssigned other= (TimeCapsuleAlreadyAssigned) obj;
            return new EqualsBuilder()
                    .append(timeCapsule, other.timeCapsule)
                    .append(snippet, other.snippet).isEquals();
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(timeCapsule)
                .append(snippet)
                .toHashCode();
    }

    @Override
    public String toString() {
        return timeCapsule + " ... - " + snippet;
    }

}
