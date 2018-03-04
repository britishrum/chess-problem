package org.afazlieva.task.view;

import org.afazlieva.task.chess.Square;
import org.afazlieva.task.solver.impl.ChessParameters;
import org.afazlieva.task.view.serialization.impl.ChessSquareEncoder;

public class ChessParametersParser implements ParametersParser<Square> {
    private final static int DEFAULT_MAX_PATH_LENGTH = 3;
    private final static int DEFAULT_BOARD_WIDTH = 8;
    private final static int DEFAULT_BOARD_HEIGHT = 8;
    private static final String INVALID_ARGUMENTS_MESSAGE = "Invalid arguments. Please specify starting and ending position.";

    private final ChessSquareEncoder squareEncoder;

    public ChessParametersParser(ChessSquareEncoder squareEncoder) {
        this.squareEncoder = squareEncoder;
    }

    public ChessParameters parse(String[] args) {
        if (isInvalidArgsLength(args)) return null;
        try {
            Square start = squareEncoder.decode(args[0]);
            Square end = squareEncoder.decode(args[1]);
            return new ChessParameters(start, end, DEFAULT_MAX_PATH_LENGTH, DEFAULT_BOARD_WIDTH, DEFAULT_BOARD_HEIGHT);
        } catch (Exception e) {
            System.out.println(INVALID_ARGUMENTS_MESSAGE);
        }
        return null;
    }

    private boolean isInvalidArgsLength(String[] args) {
        boolean result = args.length != 2;
        if (result) {
            System.out.println(INVALID_ARGUMENTS_MESSAGE);
        }
        return result;
    }
}
