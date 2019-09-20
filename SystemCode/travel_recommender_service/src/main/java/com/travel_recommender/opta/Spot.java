package com.travel_recommender.opta;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import com.travel_recommender.opta.TimeCapsule;

import java.util.List;
import java.lang.Integer;

public class Spot extends AbstractPersistable {

	private int spotId;
	private int est_duration;
	private boolean gourmet;
	private double xLongitude;
	private double yLatitude;
	private List<Integer> openTimes;
	private List<Integer> closeTimes;
	private float score;

	public int getTravelRequiredTimeCapNum(Spot other, Day day) {
		double dist_earth = 6378;
		double latitudeDifference = other.getyLatitude() * Math.PI / 180.0
				- yLatitude * Math.PI / 180.0;
		double longitudeDifference = other.getxLongitude() * Math.PI / 180.0
				- xLongitude * Math.PI / 180.0;
		double distance = 2 * Math.asin(Math.sqrt(Math.pow(
				Math.sin(latitudeDifference / 2), 2)
				+ Math.cos(other.getyLatitude() * Math.PI / 180.0)
				* Math.cos(yLatitude * Math.PI / 180.0)
				* Math.pow(Math.sin(longitudeDifference / 2), 2)));

		return (int) Math.ceil((float) (distance * dist_earth) / (0.3 * day.getCapsuleDuration()));
	}

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

	public boolean isGourmet() {
		return gourmet;
	}

	public void setGourmet(boolean gourmet) {
		gourmet = gourmet;
	}

	public double getxLongitude() {
		return xLongitude;
	}

	public void setxLongitude(double xLongitude) {
		this.xLongitude = xLongitude;
	}

	public double getyLatitude() {
		return yLatitude;
	}

	public void setyLatitude(double yLatitude) {
		this.yLatitude = yLatitude;
	}

	public List<Integer> getOpenTimes() {
		return openTimes;
	}

	public void setOpenTimes(List<Integer> openTimes) {
		this.openTimes = openTimes;
	}

	public List<Integer> getCloseTimes() {
		return closeTimes;
	}

	public void setCloseTimes(List<Integer> closeTimes) {
		this.closeTimes = closeTimes;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public int getRequiredTimeCapsuleNum(Day day) {
		return (int) Math.ceil((float) est_duration / day.getCapsuleDuration());
	}

	public int getOpenTimeCapId(Day day) {
		int openTime = this.openTimes.get(day.getWeek_day());
		return day.getRelativeTimeCapsuleIndex(openTime);
	}

	public int getCloseTimeCapId(Day day) {
		int closeTime = this.closeTimes.get(day.getWeek_day());
		return day.getRelativeTimeCapsuleIndex(closeTime);
	}

	public Spot() {
		super();
	}

	public Spot(Long id,
				int spotId,
				int est_duration,
				boolean gourmet,
				double xLongitude,
				double yLatitude,
				List<Integer> openTimes,
				List<Integer> closeTimes,
				float score) {
		super(id);
		this.spotId = spotId;
		this.est_duration = est_duration;
		this.gourmet = gourmet;
		this.xLongitude = xLongitude;
		this.yLatitude = yLatitude;
		this.openTimes = openTimes;
		this.closeTimes = closeTimes;
		this.score = score;
	}

	@Override
	public String toString() {
		return "Spot{" +
				"spotId=" + spotId +
				", est_duration=" + est_duration +
				", gourmet=" + gourmet +
				", xLongitude=" + xLongitude +
				", yLatitude=" + yLatitude +
				", openTimes=" + openTimes +
				", closeTimes=" + closeTimes +
				", score=" + score +
				", id=" + id +
				'}';
	}
}
