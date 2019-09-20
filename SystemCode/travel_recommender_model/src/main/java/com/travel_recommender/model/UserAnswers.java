package com.travel_recommender.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UserAnswers implements Serializable {

    private int qnsDepartureTime;   // time-date represented in minute
    private int qnsLeavingTime;     // time-date represented in minute
    private int qnsCountryId;
    private boolean qnsKidElder;

    // Coarse Spot Types from First Page
    private boolean qnsFoodExpectation;
    private boolean qnsCultural;
    private boolean qnsCrowded;
    private long qnsBudget;
    private boolean qnsSouveniers;
    private boolean qnsDowntown;
    private boolean qnsView;
    private boolean qnsNatural;

    // Inferable middle variables
    private boolean Family;
    private boolean Downtown;
    private boolean Bustle;
    private boolean Shopping;
    private boolean Gourmet;
    private boolean Cultural;
    private boolean Resort;

    // Concrete Spot Types
    private boolean Gardens;
    private boolean Parks;
    private boolean Museums;
    private boolean Observation_deck;
    private boolean Zoo;
    private boolean Themeparks;
    private boolean Neighbourhoods;
    private boolean Religious_Sites;
    private boolean Landmarks;
    private boolean Historical_Sites;
    private boolean Island;
    private boolean Shopping_Malls;
    private boolean Bridges;
    private boolean Beaches;

    public int getQnsDepartureTime() {
        return qnsDepartureTime;
    }

    public void setQnsDepartureTime(int qnsDepartureTime) {
        this.qnsDepartureTime = qnsDepartureTime;
    }

    public int getQnsLeavingTime() {
        return qnsLeavingTime;
    }

    public void setQnsLeavingTime(int qnsLeavingTime) {
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

    public boolean isResort() {
        return Resort;
    }

    public void setResort(boolean resort) {
        Resort = resort;
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

    public boolean isFamily() {
        return Family;
    }

    public void setFamily(boolean family) {
        Family = family;
    }

    public boolean isDowntown() {
        return Downtown;
    }

    public void setDowntown(boolean downtown) {
        Downtown = downtown;
    }

    public boolean isBustle() {
        return Bustle;
    }

    public void setBustle(boolean bustle) {
        Bustle = bustle;
    }

    public boolean isShopping() {
        return Shopping;
    }

    public void setShopping(boolean shopping) {
        Shopping = shopping;
    }

    public boolean isGourmet() {
        return Gourmet;
    }

    public void setGourmet(boolean gourmet) {
        Gourmet = gourmet;
    }

    public boolean isCultural() {
        return Cultural;
    }

    public void setCultural(boolean cultural) {
        Cultural = cultural;
    }

    public boolean isGardens() {
        return Gardens;
    }

    public void setGardens(boolean gardens) {
        Gardens = gardens;
    }

    public boolean isParks() {
        return Parks;
    }

    public void setParks(boolean parks) {
        Parks = parks;
    }

    public boolean isMuseums() {
        return Museums;
    }

    public void setMuseums(boolean museums) {
        Museums = museums;
    }

    public boolean isObservation_deck() {
        return Observation_deck;
    }

    public void setObservation_deck(boolean observation_deck) {
        Observation_deck = observation_deck;
    }

    public boolean isZoo() {
        return Zoo;
    }

    public void setZoo(boolean zoo) {
        Zoo = zoo;
    }

    public boolean isThemeparks() {
        return Themeparks;
    }

    public void setThemeparks(boolean themeparks) {
        Themeparks = themeparks;
    }

    public boolean isNeighbourhoods() {
        return Neighbourhoods;
    }

    public void setNeighbourhoods(boolean neighbourhoods) {
        Neighbourhoods = neighbourhoods;
    }

    public boolean isReligious_Sites() {
        return Religious_Sites;
    }

    public void setReligious_Sites(boolean religious_Sites) {
        Religious_Sites = religious_Sites;
    }

    public boolean isLandmarks() {
        return Landmarks;
    }

    public void setLandmarks(boolean landmarks) {
        Landmarks = landmarks;
    }

    public boolean isHistorical_Sites() {
        return Historical_Sites;
    }

    public void setHistorical_Sites(boolean historical_Sites) {
        Historical_Sites = historical_Sites;
    }

    public boolean isIsland() {
        return Island;
    }

    public void setIsland(boolean island) {
        Island = island;
    }

    public boolean isShopping_Malls() {
        return Shopping_Malls;
    }

    public void setShopping_Malls(boolean shopping_Malls) {
        Shopping_Malls = shopping_Malls;
    }

    public boolean isBridges() {
        return Bridges;
    }

    public void setBridges(boolean bridges) {
        Bridges = bridges;
    }

    public boolean isBeaches() {
        return Beaches;
    }

    public void setBeaches(boolean beaches) {
        Beaches = beaches;
    }

    public boolean isQnsNatural() {
        return qnsNatural;
    }

    public void setQnsNatural(boolean qnsNatural) {
        this.qnsNatural = qnsNatural;
    }

    @Override
    public String toString() {
        return "UserAnswers{" +
                "qnsDepartureTime=" + qnsDepartureTime +
                ", qnsLeavingTime=" + qnsLeavingTime +
                ", qnsCountryId=" + qnsCountryId +
                ", qnsKidElder=" + qnsKidElder +
                ", qnsFoodExpectation=" + qnsFoodExpectation +
                ", qnsCultural=" + qnsCultural +
                ", qnsCrowded=" + qnsCrowded +
                ", qnsBudget=" + qnsBudget +
                ", qnsSouveniers=" + qnsSouveniers +
                ", qnsDowntown=" + qnsDowntown +
                ", qnsView=" + qnsView +
                ", qnsNatural=" + qnsNatural +
                ", Family=" + Family +
                ", Downtown=" + Downtown +
                ", Bustle=" + Bustle +
                ", Shopping=" + Shopping +
                ", Gourmet=" + Gourmet +
                ", Cultural=" + Cultural +
                ", Resort=" + Resort +
                ", Gardens=" + Gardens +
                ", Parks=" + Parks +
                ", Museums=" + Museums +
                ", Observation_deck=" + Observation_deck +
                ", Zoo=" + Zoo +
                ", Themeparks=" + Themeparks +
                ", Neighbourhoods=" + Neighbourhoods +
                ", Religious_Sites=" + Religious_Sites +
                ", Landmarks=" + Landmarks +
                ", Historical_Sites=" + Historical_Sites +
                ", Island=" + Island +
                ", Shopping_Malls=" + Shopping_Malls +
                ", Bridges=" + Bridges +
                ", Beaches=" + Beaches +
                '}';
    }

    public UserAnswers() {
        this.Gourmet = true;
        this.Cultural = true;
        this.Downtown = true;
        this.Bustle = true;
        this.Family = true;
        this.Resort = true;
        this.Shopping = true;
        this.Gardens = true;
        this.Parks = true;
        this.Museums = true;
        this.Observation_deck = true;
        this.Zoo = true;
        this.Themeparks = true;
        this.Neighbourhoods = true;
        this.Religious_Sites = true;
        this.Landmarks = true;
        this.Historical_Sites = true;
        this.Island = true;
        this.Shopping_Malls = true;
        this.Bridges = true;
        this.Beaches = true;
    }

    public static Object getObjAttr(Object obj, String name) {
        for (Field field : obj.getClass().getDeclaredFields()) {
            String varName = field.getName();
            if (varName.equalsIgnoreCase(name)) {
                try {
                    return field.get(obj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }
}