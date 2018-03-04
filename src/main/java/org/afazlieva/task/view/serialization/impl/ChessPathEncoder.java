package org.afazlieva.task.view.serialization.impl;

import org.afazlieva.task.chess.Square;
import org.afazlieva.task.view.serialization.api.PathEncoder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ChessPathEncoder implements PathEncoder<Square> {

    private final ChessSquareEncoder cellEncoder;

    public ChessPathEncoder(ChessSquareEncoder squareEncoder) {
        Objects.requireNonNull(squareEncoder);
        this.cellEncoder = squareEncoder;
    }

    /**
     * Serializes chess path to string
     *
     * @param path a list of squares defining a path
     * @return a string showing a sequence of moves in a path
     */
    public String encode(List<Square> path) {
        Objects.requireNonNull(path);
        return path.stream().map(cellEncoder::encode).collect(Collectors.joining(" -> "));
    }
}
