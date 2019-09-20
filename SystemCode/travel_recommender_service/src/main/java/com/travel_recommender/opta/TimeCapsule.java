package com.travel_recommender.opta;

public class TimeCapsule extends AbstractPersistable {

    private int score;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public TimeCapsule() {
		super();
	}

	public TimeCapsule(Long id, int score) {
		super(id);
		this.score = score;
	}

	@Override
	public String toString() {
		return "TimeCapsule{" +
				"score=" + score +
				", id=" + id +
				'}';
	}
}
