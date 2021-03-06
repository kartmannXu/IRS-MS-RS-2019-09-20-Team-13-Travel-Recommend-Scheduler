package com.travel_recommender.opta;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.drools.ProblemFactCollectionProperty;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;

import java.util.List;
import com.travel_recommender.opta.Spot;
import com.travel_recommender.opta.Day;
import org.optaplanner.persistence.jaxb.api.score.buildin.hardsoft.HardSoftScoreJaxbXmlAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * This class was automatically generated by the data modeler tool.
 */

@PlanningSolution
public class Solution extends AbstractPersistable {

	//static final long serialVersionUID = 1L;
	private HardSoftScore score;
	private List<Spot> spot_list;
	private List<Day> day_list;
	private List<TimeCapsule> timeCapsules;
	private List<SpotSnippet> snippetList;

	@PlanningScore
	public HardSoftScore getScore() {
		return this.score;
	}

	public void setScore(HardSoftScore score) {
		this.score = score;
	}

	@ProblemFactCollectionProperty
	public java.util.List<Spot> getSpot_list() {
		return this.spot_list;
	}

	public void setSpot_list(
			java.util.List<Spot> spot_list) {
		this.spot_list = spot_list;
	}

	@ProblemFactCollectionProperty
	public java.util.List<Day> getDay_list() {
		return this.day_list;
	}

	public void setDay_list(
			java.util.List<Day> day_list) {
		this.day_list = day_list;
	}

	@ProblemFactCollectionProperty
	@ValueRangeProvider(id="timeRange")
	public List<TimeCapsule> getTimeCapsules() {
		return timeCapsules;
	}

	public void setTimeCapsules(List<TimeCapsule> timeCapsules) {
		this.timeCapsules = timeCapsules;
	}

	@PlanningEntityCollectionProperty
	public List<SpotSnippet> getSnippetList() {
		return snippetList;
	}

	public void setSnippetList(List<SpotSnippet> snippetList) {
		this.snippetList = snippetList;
	}

	public Solution() {
		super();
	}

	public Solution(Long id,
					HardSoftScore score,
					List<Spot> spot_list,
					List<Day> day_list,
					List<TimeCapsule> timeCapsules,
					List<SpotSnippet> snippetList) {
		super(id);
		this.score = score;
		this.spot_list = spot_list;
		this.day_list = day_list;
		this.timeCapsules = timeCapsules;
		this.snippetList = snippetList;
	}

	@Override
	public String toString() {
		return "Solution{" +
				"score=" + score +
				", spot_list=" + spot_list +
				", day_list=" + day_list +
				", timeCapsules=" + timeCapsules +
				", snippetList=" + snippetList +
				", id=" + id +
				'}';
	}
}
