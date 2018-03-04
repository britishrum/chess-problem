package org.afazlieva.task.validation;

import org.afazlieva.task.chess.Square;
import org.afazlieva.task.solver.impl.ChessParameters;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChessParametersValidatorTest {

    private ChessParametersValidator validator = new ChessParametersValidator();

    @Test
    void testValidParameters() throws ParametersValidationException {
        ChessParameters parameters = new ChessParameters(
                new Square(1, 2), new Square(2, 4),
                1, 8, 8);
        validator.validate(parameters);
    }

    @Test
    void testNullParameters() {
        assertThrows(ParametersValidationException.class, () -> {
            validator.validate(null);
        });
    }

    @Test
    void testBoardWidthLessThen1() {
        assertThrows(ParametersValidationException.class, () -> {
            ChessParameters parameters = new ChessParameters(
                    new Square(1, 2), new Square(2, 4),
                    1, -1, 8);
            validator.validate(parameters);
        });
    }

    @Test
    void testBoardHeightLessThen1() {
        assertThrows(ParametersValidationException.class, () -> {
            ChessParameters parameters = new ChessParameters(
                    new Square(1, 2), new Square(2, 4),
                    1, 8, -1);
            validator.validate(parameters);
        });
    }

    @Test
    void testBoardMovesLessThen0() {
        assertThrows(ParametersValidationException.class, () -> {
            ChessParameters parameters = new ChessParameters(
                    new Square(1, 2), new Square(2, 4),
                    -1, 8, 8);
            validator.validate(parameters);
        });
    }

    @Test
    void testBoardStartNotWithinBoard() {
        assertThrows(ParametersValidationException.class, () -> {
            ChessParameters parameters = new ChessParameters(
                    new Square(10, 20), new Square(2, 4),
                    1, 8, 8);
            validator.validate(parameters);
        });
    }

    @Test
    void testBoardEndNotWithinBoard() {
        assertThrows(ParametersValidationException.class, () -> {
            ChessParameters parameters = new ChessParameters(
                    new Square(1, 2), new Square(20, 40),
                    1, 8, 8);
            validator.validate(parameters);
        });
    }
}
