package org.afazlieva.task.solver.impl;

import org.afazlieva.task.chess.Square;
import org.afazlieva.task.solver.api.Parameters;

import java.util.Objects;

public class ChessParameters implements Parameters<Square> {
    private final Square start;
    private final Square end;
    private final int maxPathLength;
    private final int boardWidth;
    private final int boardHeight;

    public ChessParameters(Square start, Square end, int maxPathLength, int boardWidth, int boardHeight) {
        Objects.requireNonNull(start);
        Objects.requireNonNull(end);
        this.start = start;
        this.end = end;
        this.maxPathLength = maxPathLength;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
    }

    public Square getStart() {
        return start;
    }

    public Square getEnd() {
        return end;
    }

    public int getMaxPathLength() {
        return maxPathLength;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }
}
