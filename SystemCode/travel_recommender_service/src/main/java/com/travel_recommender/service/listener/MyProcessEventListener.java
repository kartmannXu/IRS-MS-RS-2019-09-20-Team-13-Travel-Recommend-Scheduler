package com.travel_recommender.service.listener;

import org.kie.api.event.process.ProcessCompletedEvent;
import org.kie.api.event.process.ProcessEventListener;
import org.kie.api.event.process.ProcessNodeLeftEvent;
import org.kie.api.event.process.ProcessNodeTriggeredEvent;
import org.kie.api.event.process.ProcessStartedEvent;
import org.kie.api.event.process.ProcessVariableChangedEvent;
import org.springframework.stereotype.Component;

@Component
public class MyProcessEventListener implements ProcessEventListener {

    @Override
    public void beforeProcessStarted(ProcessStartedEvent event) {
        System.out.println("beforeProcessStarted " + event);
    }

    @Override
    public void afterProcessStarted(ProcessStartedEvent event) {
        System.out.println("afterProcessStarted " + event);
    }

    @Override
    public void beforeProcessCompleted(ProcessCompletedEvent event) {
        System.out.println("beforeProcessCompleted " + event);
    }

    @Override
    public void afterProcessCompleted(ProcessCompletedEvent event) {
        System.out.println("afterProcessCompleted " + event);
    }

    @Override
    public void beforeNodeTriggered(ProcessNodeTriggeredEvent event) {
        System.out.println("beforeNodeTriggered " + event);
    }

    @Override
    public void afterNodeTriggered(ProcessNodeTriggeredEvent event) {
        System.out.println("afterNodeTriggered " + event);
    }

    @Override
    public void beforeNodeLeft(ProcessNodeLeftEvent event) {
        System.out.println("beforeNodeLeft " + event);
    }

    @Override
    public void afterNodeLeft(ProcessNodeLeftEvent event) {
        System.out.println("afterNodeLeft " + event);
    }

    @Override
    public void beforeVariableChanged(ProcessVariableChangedEvent event) {
        System.out.println("beforeVariableChanged " + event);
    }

    @Override
    public void afterVariableChanged(ProcessVariableChangedEvent event) {
        System.out.println("afterVariableChanged " + event);
    }

}