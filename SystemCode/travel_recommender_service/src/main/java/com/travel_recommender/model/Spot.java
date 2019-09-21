package com.travel_recommender.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Spot {

    @Id
    private int Spot_id;

    private int Country_id;
    private int City_id;
    private int Postal;
    private float lgt;
    private float ltt;
    private String Spot_name;
    private String Open_times;
    private String Close_times;
    private String Special_close_date;
    private int Est_duration;
    private float Score;
    private float Ticket;

    // Coarse Spot Types
    private float Gourmet;
    private float Cultural;
    private float Bustle;
    private float Family;
    private float Shopping;
    private float Resort;
    private float Downtown;

    // Concrete Spot Types
    private int Gardens;
    private int Parks;
    private int Museums;
    private int Observation_deck;
    private int Zoo;
    private int Themeparks;
    private int Neighbourhoods;
    private int Religious_Sites;
    private int Landmarks;
    private int Historical_Sites;
    private int Island;
    private int Shopping_Malls;
    private int Bridges;
    private int Beaches;
    private String imageUrl;
    @Column(name="introduction", length=1024)
    private String introduction;
    private String Addr;

    public float getLgt() {
        return lgt;
    }

    public void setLgt(float lgt) {
        this.lgt = lgt;
    }

    public float getLtt() {
        return ltt;
    }

    public void setLtt(float ltt) {
        this.ltt = ltt;
    }

    public float getDowntown() {
        return Downtown;
    }

    public void setDowntown(float downtown) {
        Downtown = downtown;
    }

    public int getCountry_id() {
        return Country_id;
    }

    public void setCountry_id(int country_id) {
        Country_id = country_id;
    }

    public int getSpot_id() {
        return Spot_id;
    }

    public void setSpot_id(int spot_id) {
        Spot_id = spot_id;
    }

    public int getCity_id() {
        return City_id;
    }

    public void setCity_id(int city_id) {
        City_id = city_id;
    }

    public int getPostal() {
        return Postal;
    }

    public void setPostal(int postal) {
        Postal = postal;
    }

    public String getSpot_name() {
        return Spot_name;
    }

    public void setAddr(int Addr){
        Addr = Addr;
    }

    public String getAddr() {
        return this.Addr;
    }

    public void setSpot_name(String spot_name) {
        Spot_name = spot_name.replace("_", " ");
    }

    public String getOpen_times() {
        return Open_times;
    }

    public void setOpen_times(String open_times) {
        Open_times = open_times;
    }

    public String getClose_times() {
        return Close_times;
    }

    public void setClose_times(String close_times) {
        Close_times = close_times;
    }

    public String getSpecial_close_date() {
        return Special_close_date;
    }

    public void setSpecial_close_date(String special_close_date) {
        Special_close_date = special_close_date;
    }

    public int getEst_duration() {
        return Est_duration;
    }

    public void setEst_duration(int est_duration) {
        Est_duration = est_duration;
    }

    public float getScore() {
        return Score;
    }

    public void setScore(float score) {
        Score = score;
    }

    public float getGourmet() {
        return Gourmet;
    }

    public void setGourmet(float gourmet) {
        Gourmet = gourmet;
    }

    public float getCultural() {
        return Cultural;
    }

    public void setCultural(float cultural) {
        Cultural = cultural;
    }

    public float getBustle() {
        return Bustle;
    }

    public void setBustle(float bustle) {
        Bustle = bustle;
    }

    public float getFamily() {
        return Family;
    }

    public void setFamily(float family) {
        Family = family;
    }

    public float getShopping() {
        return Shopping;
    }

    public void setShopping(float shopping) {
        Shopping = shopping;
    }

    public float getResort() {
        return Resort;
    }

    public void setResort(float resort) {
        Resort = resort;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public float getTicket() {
        return Ticket;
    }

    public void setTicket(float ticket) {
        Ticket = ticket;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getGardens() {
        return Gardens;
    }

    public void setGardens(int gardens) {
        Gardens = gardens;
    }

    public int getParks() {
        return Parks;
    }

    public void setParks(int parks) {
        Parks = parks;
    }

    public int getMuseums() {
        return Museums;
    }

    public void setMuseums(int museums) {
        Museums = museums;
    }

    public int getObservation_deck() {
        return Observation_deck;
    }

    public void setObservation_deck(int observation_deck) {
        Observation_deck = observation_deck;
    }

    public int getZoo() {
        return Zoo;
    }

    public void setZoo(int zoo) {
        Zoo = zoo;
    }

    public int getThemeparks() {
        return Themeparks;
    }

    public void setThemeparks(int themeparks) {
        Themeparks = themeparks;
    }

    public int getNeighbourhoods() {
        return Neighbourhoods;
    }

    public void setNeighbourhoods(int neighbourhoods) {
        Neighbourhoods = neighbourhoods;
    }

    public int getReligious_Sites() {
        return Religious_Sites;
    }

    public void setReligious_Sites(int religious_Sites) {
        Religious_Sites = religious_Sites;
    }

    public int getLandmarks() {
        return Landmarks;
    }

    public void setLandmarks(int landmarks) {
        Landmarks = landmarks;
    }

    public int getHistorical_Sites() {
        return Historical_Sites;
    }

    public void setHistorical_Sites(int historical_Sites) {
        Historical_Sites = historical_Sites;
    }

    public int getIsland() {
        return Island;
    }

    public void setIsland(int island) {
        Island = island;
    }

    public int getShopping_Malls() {
        return Shopping_Malls;
    }

    public void setShopping_Malls(int shopping_Malls) {
        Shopping_Malls = shopping_Malls;
    }

    public int getBridges() {
        return Bridges;
    }

    public void setBridges(int bridges) {
        Bridges = bridges;
    }

    public int getBeaches() {
        return Beaches;
    }

    public void setBeaches(int beaches) {
        Beaches = beaches;
    }

    public void setAddr(String addr) {
        Addr = addr;
    }

    @Override
    public String toString() {
        return "Spot{" +
                "Spot_id=" + Spot_id +
                ", Country_id=" + Country_id +
                ", City_id=" + City_id +
                ", Postal=" + Postal +
                ", lgt=" + lgt +
                ", ltt=" + ltt +
                ", Spot_name='" + getSpot_name() + '\'' +
                ", Open_times='" + Open_times + '\'' +
                ", Close_times='" + Close_times + '\'' +
                ", Special_close_date='" + Special_close_date + '\'' +
                ", Est_duration=" + Est_duration +
                ", Score=" + Score +
                ", Ticket=" + Ticket +
                ", Gourmet=" + Gourmet +
                ", Cultural=" + Cultural +
                ", Bustle=" + Bustle +
                ", Family=" + Family +
                ", Shopping=" + Shopping +
                ", Resort=" + Resort +
                ", Downtown=" + Downtown +
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
                ", imageUrl=" + imageUrl +
                ", introduction=" + introduction +
                ", Addr=" + Addr + 
                '}';
    }

    public float getElectiveScore(UserAnswers userAns) {
        Map<String, Boolean> elects = new HashMap<>();
        elects.put("Gourmet", userAns.isGourmet());
        elects.put("Cultural", userAns.isQnsCultural());
        elects.put("Bustle", userAns.isBustle());
        elects.put("Family", userAns.isFamily());
        elects.put("Shopping", userAns.isShopping());
        elects.put("Resort", userAns.isResort());
        elects.put("Downtown", userAns.isQnsDowntown());

        Map<String, Float> scores = new HashMap<>();
        scores.put("Gourmet", getGourmet());
        scores.put("Cultural", getCultural());
        scores.put("Bustle", getBustle());
        scores.put("Family", getFamily());
        scores.put("Shopping", getShopping());
        scores.put("Resort", getResort());
        scores.put("Downtown", getDowntown());

        float electiveScore = 0.0f;
        for (String type: elects.keySet()) {
            electiveScore += elects.get(type)? scores.get(type): 0;
        }
        return Score * electiveScore / 7;
    }
}
