package com.travel_recommender.service.scheduleSolver;

import org.optaplanner.core.api.score.Score;

public interface SolverManager<Solution_> {
    void solve(Comparable<?> clientId, Solution_ planningProblem);

    Solution_ getBestSolution(Comparable<?> clientId);

    Score getBestScore(Comparable<?> clientId);

    SolverStatus getSolverStatus(Comparable<?> clientId);
}
