package com.travel_recommender.service.viewModel;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UserAnswerViewModel implements Serializable {
    private long id;
    @NotNull private String qnsDepartureTime;   // your estimated departure date?
    @NotNull private String qnsLeavingTime;     // your estimated leaving date?
    @NotNull private int qnsCountryId;       // which country do you prefer?
    @NotNull private boolean qnsKidElder;    // are you travelling with any kid or elder?
    @NotNull private boolean qnsFoodExpectation; // do you have high expectation for food?
    @NotNull private boolean qnsCultural;    // Are you prompt to immersive cultural experience?
    @NotNull private boolean qnsCrowded;     // can you mind crowded attractions when travelling?
    private long qnsBudget = Long.MAX_VALUE; // could you estimate your budget for the whole trip? (optional)
    @NotNull private boolean qnsSouveniers;  // do you plan to buy souvenirs?
    @NotNull private boolean qnsDowntown;    // do you enjoy downtown places?
    @NotNull private boolean qnsView;        // do you enjoy watching city skyline and landscape?
    @NotNull private boolean qnsNatural;        // how about some refreshment with animals and plants?

    private String types = "";               // e.g. "Park,Museum"

    public String getQnsDepartureTime() {
        return qnsDepartureTime;
    }

    public void setQnsDepartureTime(String qnsDepartureTime) {
        this.qnsDepartureTime = qnsDepartureTime;
    }

    public String getQnsLeavingTime() {
        return qnsLeavingTime;
    }

    public void setQnsLeavingTime(String qnsLeavingTime) {
        this.qnsLeavingTime = qnsLeavingTime;
    }

    public int getQnsCountryId() {
        return qnsCountryId;
    }

    public void setQnsCountryId(int qnsCountryId) {
        this.qnsCountryId = qnsCountryId;
    }

    public boolean isQnsKidElder() {
        return qnsKidElder;
    }

    public void setQnsKidElder(boolean qnsKidElder) {
        this.qnsKidElder = qnsKidElder;
    }

    public boolean isQnsFoodExpectation() {
        return qnsFoodExpectation;
    }

    public void setQnsFoodExpectation(boolean qnsFoodExpectation) {
        this.qnsFoodExpectation = qnsFoodExpectation;
    }

    public boolean isQnsCultural() {
        return qnsCultural;
    }

    public void setQnsCultural(boolean qnsCultural) {
        this.qnsCultural = qnsCultural;
    }

    public boolean isQnsCrowded() {
        return qnsCrowded;
    }

    public void setQnsCrowded(boolean qnsCrowded) {
        this.qnsCrowded = qnsCrowded;
    }

    public long getQnsBudget() {
        return qnsBudget;
    }

    public void setQnsBudget(long qnsBudget) {
        this.qnsBudget = qnsBudget;
    }

    public boolean isQnsSouveniers() {
        return qnsSouveniers;
    }

    public void setQnsSouveniers(boolean qnsSouveniers) {
        this.qnsSouveniers = qnsSouveniers;
    }

    public boolean isQnsDowntown() {
        return qnsDowntown;
    }

    public void setQnsDowntown(boolean qnsDowntown) {
        this.qnsDowntown = qnsDowntown;
    }

    public boolean isQnsView() {
        return qnsView;
    }

    public void setQnsView(boolean qnsView) {
        this.qnsView = qnsView;
    }

    public boolean isQnsNatural() {
        return qnsNatural;
    }

    public void setQnsNatural(boolean qnsNatural) {
        this.qnsNatural = qnsNatural;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types_temp) {
    	types = types_temp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
