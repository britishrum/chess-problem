package org.afazlieva.task;

import org.afazlieva.task.chess.Square;
import org.afazlieva.task.solver.impl.ChessParameters;
import org.afazlieva.task.validation.ChessParametersValidator;
import org.afazlieva.task.validation.ParametersValidationException;
import org.afazlieva.task.validation.ParametersValidator;
import org.afazlieva.task.view.ChessParametersParser;
import org.afazlieva.task.view.serialization.api.PathEncoder;
import org.afazlieva.task.view.serialization.impl.ChessPathEncoder;
import org.afazlieva.task.view.serialization.impl.ChessSquareEncoder;
import org.afazlieva.task.view.serialization.impl.LexicalEncoder;

import java.lang.reflect.Executable;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public class Cli {

    /**
     * @param args starting and ending position, both defined as chess squares. Example: A1 B3
     */
    public static void main(String[] args) {
        // Creating objects for dependency injection
        LexicalEncoder lexicalEncoder = new LexicalEncoder();
        ChessSquareEncoder squareEncoder = new ChessSquareEncoder(lexicalEncoder);
        PathEncoder<Square> chessPathEncoder = new ChessPathEncoder(squareEncoder);
        ChessParametersParser parametersParser = new ChessParametersParser(squareEncoder);

        try {
            // Parsing and validating input parameters
            ChessParameters parameters = parametersParser.parse(args);
            Objects.requireNonNull(parameters);
            ParametersValidator<ChessParameters> parametersValidator = new ChessParametersValidator();
            parametersValidator.validate(parameters);

            // Searching for solution to the problem
            ChessApp chessApp = new ChessApp(chessPathEncoder, parameters);
            Collection<String> results = chessApp.run();

            // Printing results
            printResults(results);
        } catch (Exception e) {
            String message = e.getMessage() == null ? "No further message provided" : e.getMessage();
            System.err.println(message);
        }
    }

    private static void printResults(Collection<String> results) {
        Objects.requireNonNull(results);
        String output;
        if (results.isEmpty()) {
            output = "No solution has been found";
        } else {
            output = results.stream().collect(Collectors.joining("\n"));
        }
        System.out.println(output);
    }
}
