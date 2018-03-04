package org.afazlieva.task.chess;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Defines strategy for finding next possible knight piece positions on an empty chess board
 */
public class KnightLookupStrategy extends ChessLookupStrategy {

    public KnightLookupStrategy(int boardWidth, int boardHeight) {
        super(boardWidth, boardHeight);
    }

    @Override
    public Set<Square> getNextValues(Square current) {
        int x = current.getX();
        int y = current.getY();

        if (!isCellWithinBoard(x, y)) {
            return Collections.emptySet();
        }

        int[][] moves = {
                {x + 1, y + 2},
                {x + 1, y - 2},
                {x - 1, y + 2},
                {x - 1, y - 2},
                {x + 2, y + 1},
                {x + 2, y - 1},
                {x - 2, y + 1},
                {x - 2, y - 1}
        };
        return Arrays.stream(moves)
                .filter(coordinate -> isCellWithinBoard(coordinate[0], coordinate[1]))
                .map(Square::new)
                .collect(Collectors.toSet());
    }
}
