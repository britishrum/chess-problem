package org.afazlieva.task;

import org.afazlieva.task.chess.KnightLookupStrategy;
import org.afazlieva.task.chess.Square;
import org.afazlieva.task.solver.api.LookupStrategy;
import org.afazlieva.task.solver.api.PathFinder;
import org.afazlieva.task.solver.impl.ChessParameters;
import org.afazlieva.task.solver.impl.ShortestPathFinder;
import org.afazlieva.task.view.serialization.api.PathEncoder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ChessApp {

    private final PathEncoder<Square> pathEncoder;
    private final ChessParameters parameters;
    private final PathFinder<Square> shortestPathFinder;

    public ChessApp(PathEncoder<Square> pathEncoder, ChessParameters parameters) {
        Objects.requireNonNull(pathEncoder);
        Objects.requireNonNull(parameters);
        this.pathEncoder = pathEncoder;
        this.parameters = parameters;
        LookupStrategy<Square> lookupStrategy = new KnightLookupStrategy(
                parameters.getBoardWidth(), parameters.getBoardHeight());
        this.shortestPathFinder = new ShortestPathFinder<>(lookupStrategy);
    }

    public List<String> run() {
        return shortestPathFinder
                .findPaths(parameters.getStart(), parameters.getEnd(), parameters.getMaxPathLength()).stream()
                .map(pathEncoder::encode)
                .collect(Collectors.toList());
    }
}