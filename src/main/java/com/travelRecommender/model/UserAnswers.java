package com.travelRecommender.model;

public class UserAnswers {

    private int qnsdepartureTime;
    private int qnsleavingTime;
    private int qnstravellingTime; // Number of days staying in the country
    private int qnscountryId;
    private boolean qnswithElder;
    private boolean qnswithKid;

    // Coarse Spot Types from First Page
    private boolean qnsGourmet;
    private boolean qnsCultural;
    private boolean qnsBustle;
    private boolean qnsPricey;
    private boolean qnsFamily;
    private boolean qnsShopping;
    private boolean qnsPopular;
    private boolean qnsResort;

    // Concrete Spot Types from Second Page
    private boolean qnsGarden;
    private boolean qnsPark;
    private boolean qnsMuseum;
    private boolean qnsScene_watching;
    private boolean qnsZoo;
    private boolean qnsThemePark;
    private boolean qnsNeighborhood;
    private boolean qnsReligious;

    public int getQnsdepartureTime() {
        return qnsdepartureTime;
    }

    public void setQnsdepartureTime(int qnsdepartureTime) {
        this.qnsdepartureTime = qnsdepartureTime;
    }

    public int getQnsleavingTime() {
        return qnsleavingTime;
    }

    public void setQnsleavingTime(int qnsleavingTime) {
        this.qnsleavingTime = qnsleavingTime;
    }

    public int getQnstravellingTime() {
        return qnstravellingTime;
    }

    public void setQnstravellingTime(int qnstravellingTime) {
        this.qnstravellingTime = qnstravellingTime;
    }

    public int getQnscountryId() {
        return qnscountryId;
    }

    public void setQnscountryId(int qnscountryId) {
        this.qnscountryId = qnscountryId;
    }

    public boolean isQnswithElder() {
        return qnswithElder;
    }

    public void setQnswithElder(boolean qnswithElder) {
        this.qnswithElder = qnswithElder;
    }

    public boolean isQnswithKid() {
        return qnswithKid;
    }

    public void setQnswithKid(boolean qnswithKid) {
        this.qnswithKid = qnswithKid;
    }

    public boolean isQnsGourmet() {
        return qnsGourmet;
    }

    public void setQnsGourmet(boolean qnsGourmet) {
        this.qnsGourmet = qnsGourmet;
    }

    public boolean isQnsCultural() {
        return qnsCultural;
    }

    public void setQnsCultural(boolean qnsCultural) {
        this.qnsCultural = qnsCultural;
    }

    public boolean isQnsBustle() {
        return qnsBustle;
    }

    public void setQnsBustle(boolean qnsBustle) {
        this.qnsBustle = qnsBustle;
    }

    public boolean isQnsPricey() {
        return qnsPricey;
    }

    public void setQnsPricey(boolean qnsPricey) {
        this.qnsPricey = qnsPricey;
    }

    public boolean isQnsFamily() {
        return qnsFamily;
    }

    public void setQnsFamily(boolean qnsFamily) {
        this.qnsFamily = qnsFamily;
    }

    public boolean isQnsShopping() {
        return qnsShopping;
    }

    public void setQnsShopping(boolean qnsShopping) {
        this.qnsShopping = qnsShopping;
    }

    public boolean isQnsPopular() {
        return qnsPopular;
    }

    public void setQnsPopular(boolean qnsPopular) {
        this.qnsPopular = qnsPopular;
    }

    public boolean isQnsResort() {
        return qnsResort;
    }

    public void setQnsResort(boolean qnsResort) {
        this.qnsResort = qnsResort;
    }

    public boolean isQnsGarden() {
        return qnsGarden;
    }

    public void setQnsGarden(boolean qnsGarden) {
        this.qnsGarden = qnsGarden;
    }

    public boolean isQnsPark() {
        return qnsPark;
    }

    public void setQnsPark(boolean qnsPark) {
        this.qnsPark = qnsPark;
    }

    public boolean isQnsMuseum() {
        return qnsMuseum;
    }

    public void setQnsMuseum(boolean qnsMuseum) {
        this.qnsMuseum = qnsMuseum;
    }

    public boolean isQnsScene_watching() {
        return qnsScene_watching;
    }

    public void setQnsScene_watching(boolean qnsScene_watching) {
        this.qnsScene_watching = qnsScene_watching;
    }

    public boolean isQnsZoo() {
        return qnsZoo;
    }

    public void setQnsZoo(boolean qnsZoo) {
        this.qnsZoo = qnsZoo;
    }

    public boolean isQnsThemePark() {
        return qnsThemePark;
    }

    public void setQnsThemePark(boolean qnsThemePark) {
        this.qnsThemePark = qnsThemePark;
    }

    public boolean isQnsNeighborhood() {
        return qnsNeighborhood;
    }

    public void setQnsNeighborhood(boolean qnsNeighborhood) {
        this.qnsNeighborhood = qnsNeighborhood;
    }

    public boolean isQnsReligious() {
        return qnsReligious;
    }

    public void setQnsReligious(boolean qnsReligious) {
        this.qnsReligious = qnsReligious;
    }

    @Override
    public String toString() {
        return "UserAnswers [" +
                "qnsdepartureTime=" + qnsdepartureTime +
                ", qnsleavingTime=" + qnsleavingTime +
                ", qnstravellingTime=" + qnstravellingTime +
                ", qnscountryId=" + qnscountryId +
                ", qnswithElder=" + qnswithElder +
                ", qnswithKid=" + qnswithKid +
                ", qnsGourmet=" + qnsGourmet +
                ", qnsCultural=" + qnsCultural +
                ", qnsBustle=" + qnsBustle +
                ", qnsPricey=" + qnsPricey +
                ", qnsFamily=" + qnsFamily +
                ", qnsShopping=" + qnsShopping +
                ", qnsPopular=" + qnsPopular +
                ", qnsResort=" + qnsResort +
                ", qnsGarden=" + qnsGarden +
                ", qnsPark=" + qnsPark +
                ", qnsMuseum=" + qnsMuseum +
                ", qnsScene_watching=" + qnsScene_watching +
                ", qnsZoo=" + qnsZoo +
                ", qnsThemePark=" + qnsThemePark +
                ", qnsNeighborhood=" + qnsNeighborhood +
                ", qnsReligious=" + qnsReligious +
                ']';
    }
}
