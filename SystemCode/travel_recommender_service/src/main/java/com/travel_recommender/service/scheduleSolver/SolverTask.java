package com.travel_recommender.service.scheduleSolver;

import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.solver.Solver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SolverTask<Solution_> implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(SolverTask.class);

    private final Comparable<?> clientId;
    private Solver<Solution_> solver;
    private Solution_ planningProblem;

    public SolverTask(Comparable<?> clientId) {
        this.clientId = clientId;
    }

    public SolverTask(Comparable<?> clientId, Solver<Solution_> solver, Solution_ planningProblem) {
        this.clientId = clientId;
        this.solver = solver;
        this.planningProblem = planningProblem;
    }

    public Solution_ getBestSolution() {
        return solver.getBestSolution();
    }

    public Score getBestScore() {
        return solver.getBestScore();
    }

    @Override
    public void run() {
        logger.info("Running solverTask for clientId ({}).", clientId);
        solver.solve(planningProblem);
    }

    public SolverStatus getSolverStatus() {
        if (solver.isTerminateEarly()) {
            return SolverStatus.TERMINATING_EARLY;
        } else if (solver.isSolving()) {
            return SolverStatus.SOLVING;
        } else {
            return SolverStatus.STOPPED;
        }
    }
}
