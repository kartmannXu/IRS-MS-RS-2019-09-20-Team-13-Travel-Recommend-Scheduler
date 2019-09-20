package com.travel_recommender.opta;

import java.io.Serializable;
import org.optaplanner.core.api.domain.lookup.PlanningId;

public abstract class AbstractPersistable implements Serializable {
    public Long id;

    @PlanningId
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AbstractPersistable() {
		super();
	}

	public AbstractPersistable(Long id) {
		super();
		this.id = id;
	}
}
