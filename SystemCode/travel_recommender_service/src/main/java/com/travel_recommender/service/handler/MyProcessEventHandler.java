package com.travel_recommender.service.handler;

import java.util.HashMap;
import java.util.Map;

import com.travel_recommender.model.UserAnswers;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;
import org.springframework.stereotype.Component;

import com.travel_recommender.model.*;

@Component("MyTravelRecommendTask")
public class MyProcessEventHandler implements WorkItemHandler{

    @Override
    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
        System.out.println("Work item my search task being executed " + workItem);
        UserAnswers param = (UserAnswers) workItem.getParameter("userAnsHolder");

        Map<String, Object> results = new HashMap<String, Object>();
        results.put("Result", param);

        manager.completeWorkItem(workItem.getId(), results);

        System.out.println("Work item result is  " + workItem.getResult("Result"));

    }

    @Override
    public void abortWorkItem(WorkItem workItem, WorkItemManager manager) { }

}
