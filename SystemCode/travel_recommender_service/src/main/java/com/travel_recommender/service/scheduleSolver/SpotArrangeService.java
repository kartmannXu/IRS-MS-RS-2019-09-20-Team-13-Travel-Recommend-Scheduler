package com.travel_recommender.service.scheduleSolver;

import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.solver.SolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class SpotArrangeService<Solution_> implements SolverManager<Solution_>  {

    private static final Logger logger = LoggerFactory.getLogger(SpotArrangeService.class);
    private ExecutorService executorService;
    private SolverFactory<Solution_> solverFactory;
    private Map<Comparable<?>, SolverTask<Solution_>> clientIdToSolverTaskMap;

    public SpotArrangeService() {
        String solverConfigPath = "config/SpotArrangeServiceScoreConfig.xml";
        solverFactory = SolverFactory.createFromXmlResource(solverConfigPath, SpotArrangeService.class.getClassLoader());
        clientIdToSolverTaskMap = new HashMap<>();
    }

    @Override
    public void solve(Comparable<?> clientId, Solution_ planningProblem) {
        synchronized (this) {
            if (clientIdToSolverTaskMap.containsKey(clientId)) {
                throw new IllegalArgumentException("Client id (" + clientId + ") already exists.");
            }
            SolverTask<Solution_> newSolverTask = new SolverTask<>(clientId, solverFactory.buildSolver(), planningProblem);
            executorService.submit(newSolverTask);
            clientIdToSolverTaskMap.put(clientId, newSolverTask);
            logger.info("A new solver task was created with clientId ({}).", clientId);
        }
    }

    @Override
    public Solution_ getBestSolution(Comparable<?> clientId) {
        logger.debug("Getting best solution of clientId ({}).", clientId);
        return clientIdToSolverTaskMap.get(clientId).getBestSolution();
    }

    @Override
    public Score getBestScore(Comparable<?> clientId) {
        logger.debug("Getting best score of clientId ({}).", clientId);
        return clientIdToSolverTaskMap.get(clientId).getBestScore();
    }

    @Override
    public SolverStatus getSolverStatus(Comparable<?> clientId) {
        logger.debug("Getting solver status of clientId ({}).", clientId);
        return clientIdToSolverTaskMap.get(clientId).getSolverStatus();
    }

    @PostConstruct
    private void init() {
        int numAvailableProcessors = Runtime.getRuntime().availableProcessors();
        logger.info("Number of available processors: {}.", numAvailableProcessors);
        executorService = Executors.newFixedThreadPool(numAvailableProcessors - 2);
    }

    @PreDestroy
    private void shutdown() {
        logger.info("Shutting down {}.", SpotArrangeService.class.getName());
        executorService.shutdownNow();
    }
}
