package com.travel_recommender.opta.move;

import com.travel_recommender.opta.*;
import org.optaplanner.core.impl.heuristic.selector.move.factory.MoveListFactory;

import java.util.*;
import java.util.stream.Collectors;

public class SpotSnippetMoveFactory implements MoveListFactory<Solution> {

    @Override
    public List<SpotSnippetMove> createMoveList(Solution solution) {
        List<SpotSnippetMove> moveList = new ArrayList<>();
        List<SpotSnippet> snippetList = solution.getSnippetList();
        Map<Spot, List<SpotSnippet>> snippetListGroupBySpot = snippetList.stream().collect(
                Collectors.groupingBy(SpotSnippet::getSpot));
        Map<Day, List<TimeCapsule>> timeCapsuleListGroupByDay = new HashMap<>();
        for (Day day : solution.getDay_list()) {
            timeCapsuleListGroupByDay.put(day, day.getTimeCapsules());
        }

        for (Map.Entry<Spot, List<SpotSnippet>> entry: snippetListGroupBySpot.entrySet()) {
            List<SpotSnippet> subSnippetList = entry.getValue();
            for (int i = 0; i < subSnippetList.size() ; ++i) {
                for (Map.Entry<Day, List<TimeCapsule>> entry1 : timeCapsuleListGroupByDay.entrySet()) {
                    List<TimeCapsule> subCapsules = entry1.getValue();
                    for (int k = 0 ; k < subCapsules.size() - 1; k ++) {
                        moveList.add(new SpotSnippetMove(subSnippetList.get(i), subCapsules.get(k)));
                        moveList.add(new SpotSnippetMove(subSnippetList.get(i + 1), subCapsules.get(k)));
                    }
                }
            }
        }
        return moveList;
    }
}
