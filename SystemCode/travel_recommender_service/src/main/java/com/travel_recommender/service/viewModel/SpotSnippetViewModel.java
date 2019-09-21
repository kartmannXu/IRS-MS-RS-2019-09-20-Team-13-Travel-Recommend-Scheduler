package com.travel_recommender.service.viewModel;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class SpotSnippetViewModel implements Serializable {
    @NotNull private int spotId;
    @NotNull private int est_duration;
    @NotNull private int timeCapsuleId;     //id in each day
    @NotNull private int day;
    @NotNull private String spotName;

    public int getSpotId() {
        return spotId;
    }

    public void setSpotId(int spotId) {
        this.spotId = spotId;
    }

    public int getEst_duration() {
        return est_duration;
    }

    public void setEst_duration(int est_duration) {
        this.est_duration = est_duration;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getSpotName() {
        return spotName;
    }

    public void setSpotName(String spotName) {
        this.spotName = spotName;
    }

    public int getTimeCapsuleId() {
        return timeCapsuleId;
    }

    public void setTimeCapsuleId(int timeCapsuleId) {
        this.timeCapsuleId = timeCapsuleId;
    }

    @Override
    public String toString() {
        return "SpotSnippetViewModel{" +
                "spotId=" + spotId +
                ", est_duration=" + est_duration +
                ", timeCapsuleId=" + timeCapsuleId +
                ", day=" + day +
                ", spotName='" + spotName + '\'' +
                '}';
    }
}
