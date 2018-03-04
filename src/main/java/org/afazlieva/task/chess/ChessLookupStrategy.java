package org.afazlieva.task.chess;

import org.afazlieva.task.solver.api.LookupStrategy;

/**
 * Defines strategy for finding next possible squares based on the current square.
 * Assumes that the board is empty.
 */
public abstract class ChessLookupStrategy implements LookupStrategy<Square> {
    private int boardWidth;
    private int boardHeight;

    public ChessLookupStrategy(int boardWidth, int boardHeight) {
        if (boardWidth < 1 || boardHeight < 1) throw new IllegalArgumentException();
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
    }

    @Override
    public boolean isValidValue(Square square) {
        return square != null && isCellWithinBoard(square.getX(), square.getY(), boardWidth, boardHeight);
    }

    public boolean isCellWithinBoard(int x, int y) {
        return isCellWithinBoard(x, y, boardWidth, boardHeight);
    }

    public static boolean isCellWithinBoard(int x, int y, int boardWidth, int boardHeight) {
        return x >= 0 && x < boardWidth
                && y >= 0 && y < boardHeight;
    }
}
