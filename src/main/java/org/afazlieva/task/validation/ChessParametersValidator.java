package org.afazlieva.task.validation;

import org.afazlieva.task.chess.ChessLookupStrategy;
import org.afazlieva.task.solver.impl.ChessParameters;

public class ChessParametersValidator implements ParametersValidator<ChessParameters> {

    public void validate(ChessParameters parameters) throws ParametersValidationException {
        if (parameters == null) throw new ParametersValidationException("Parameters are null");
        if (parameters.getBoardWidth() < 1) throw new ParametersValidationException("Invalid board width value");
        if (parameters.getBoardHeight() < 1) throw new ParametersValidationException("Invalid board height value");
        if (parameters.getMaxPathLength() < 0) throw new ParametersValidationException("Invalid moves value");
        if (!ChessLookupStrategy.isCellWithinBoard(parameters.getStart().getX(), parameters.getStart().getY(),
                parameters.getBoardWidth(), parameters.getBoardHeight()))
            throw new ParametersValidationException("Invalid starting position: must be within the board");
        if (!ChessLookupStrategy.isCellWithinBoard(parameters.getEnd().getX(), parameters.getEnd().getY(),
                parameters.getBoardWidth(), parameters.getBoardHeight()))
            throw new ParametersValidationException("Invalid ending position: must be within the board");

    }
}
