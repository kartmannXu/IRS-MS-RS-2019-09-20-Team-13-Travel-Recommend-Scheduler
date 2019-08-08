package com.travelRecommender.model;

import javax.persistence.Entity;
import javax.persistence.Id;

//Todo: varify date in service module

@Entity
public class SearchCriteria{

    @Id
    private int Spot_id;
    private int City_id;
    private int Postal;
    private String Spot_name;
    private String Open_times;
    private String Close_times;
    private String Special_open_date;
    private String Special_close_date;
    private String Est_duration;
    private String score;


    // Coarse Spot Types
    private boolean Gourmet;
    private boolean Cultural;
    private boolean Bustle;
    private boolean Pricey;
    private boolean Family;
    private boolean Shopping;
    private boolean Popular;
    private boolean Resort;

    // Concrete Spot Types
    private boolean Garden;
    private boolean Park;
    private boolean Museum;
    private boolean Scene_watching;
    private boolean Zoo;
    private boolean ThemePark;
    private boolean Neighborhood;
    private boolean Religious;


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

    public boolean isBustle() {
        return Bustle;
    }

    public void setBustle(boolean bustle) {
        Bustle = bustle;
    }

    public boolean isPricey() {
        return Pricey;
    }

    public void setPricey(boolean pricey) {
        Pricey = pricey;
    }

    public boolean isFamily() {
        return Family;
    }

    public void setFamily(boolean family) {
        Family = family;
    }

    public boolean isShopping() {
        return Shopping;
    }

    public void setShopping(boolean shopping) {
        Shopping = shopping;
    }

    public boolean isPopular() {
        return Popular;
    }

    public void setPopular(boolean popular) {
        Popular = popular;
    }

    public boolean isResort() {
        return Resort;
    }

    public void setResort(boolean resort) {
        Resort = resort;
    }

    public boolean isGarden() {
        return Garden;
    }

    public void setGarden(boolean garden) {
        Garden = garden;
    }

    public boolean isPark() {
        return Park;
    }

    public void setPark(boolean park) {
        Park = park;
    }

    public boolean isMuseum() {
        return Museum;
    }

    public void setMuseum(boolean museum) {
        Museum = museum;
    }

    public boolean isScene_watching() {
        return Scene_watching;
    }

    public void setScene_watching(boolean scene_watching) {
        Scene_watching = scene_watching;
    }

    public boolean isZoo() {
        return Zoo;
    }

    public void setZoo(boolean zoo) {
        Zoo = zoo;
    }

    public boolean isThemePark() {
        return ThemePark;
    }

    public void setThemePark(boolean themePark) {
        ThemePark = themePark;
    }

    public boolean isNeighborhood() {
        return Neighborhood;
    }

    public void setNeighborhood(boolean neighborhood) {
        Neighborhood = neighborhood;
    }

    public boolean isReligious() {
        return Religious;
    }

    public void setReligious(boolean religious) {
        Religious = religious;
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

    public void setSpot_name(String spot_name) {
        Spot_name = spot_name;
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

    public String getSpecial_open_date() {
        return Special_open_date;
    }

    public void setSpecial_open_date(String special_open_date) {
        Special_open_date = special_open_date;
    }

    public String getSpecial_close_date() {
        return Special_close_date;
    }

    public void setSpecial_close_date(String special_close_date) {
        Special_close_date = special_close_date;
    }

    public String getEst_duration() {
        return Est_duration;
    }

    public void setEst_duration(String est_duration) {
        Est_duration = est_duration;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
    /**
    private void varifyDates () {
        Date currentDate = new Date();
        int relativeTime = (int) (this.departureTime.getTime() - currentDate.getTime());
        assert (relativeTime >= 0) : "You should departure in the future.";
        this.travellingTime = (int) (this.leavingTime.getTime() - this.departureTime.getTime()) / (1000 * 3600 * 24);
        assert (this.travellingTime >= 0) : "You should leave after departure.";
    }*/

    @Override
    public String toString() {
        return "SearchCriteria [" +
                "Spot_id=" + Spot_id +
                ", City_id=" + City_id +
                ", Postal=" + Postal +
                ", Spot_name='" + Spot_name + '\'' +
                ", Open_times='" + Open_times + '\'' +
                ", Close_times='" + Close_times + '\'' +
                ", Special_open_date='" + Special_open_date + '\'' +
                ", Special_close_date='" + Special_close_date + '\'' +
                ", Est_duration='" + Est_duration + '\'' +
                ", score='" + score + '\'' +
                ", Gourmet=" + Gourmet +
                ", Cultural=" + Cultural +
                ", Bustle=" + Bustle +
                ", Pricey=" + Pricey +
                ", Family=" + Family +
                ", Shopping=" + Shopping +
                ", Popular=" + Popular +
                ", Resort=" + Resort +
                ", Garden=" + Garden +
                ", Park=" + Park +
                ", Museum=" + Museum +
                ", Scene_watching=" + Scene_watching +
                ", Zoo=" + Zoo +
                ", ThemePark=" + ThemePark +
                ", Neighborhood=" + Neighborhood +
                ", Religious=" + Religious +
                ']';
    }
}
