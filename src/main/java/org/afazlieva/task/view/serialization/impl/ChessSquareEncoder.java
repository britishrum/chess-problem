package org.afazlieva.task.view.serialization.impl;

import org.afazlieva.task.chess.Square;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChessSquareEncoder {

    private static final Pattern LETTERS_PATTERN = Pattern.compile("^[a-zA-Z]+");
    private final LexicalEncoder lexicalEncoder;

    public ChessSquareEncoder(LexicalEncoder lexicalEncoder) {
        Objects.requireNonNull(lexicalEncoder);
        this.lexicalEncoder = lexicalEncoder;
    }

    /**
     * Serializes a chess square to string.
     *
     * @param square a chess square
     * @return a string representation of chess square position.
     */
    public String encode(Square square) {
        Objects.requireNonNull(square);
        return lexicalEncoder.encode(square.getX()) + (square.getY() + 1);
    }

    /**
     * Deerializes string to a chess square.
     *
     * @param string a string representation of a chess square
     * @return chess square
     */
    public Square decode(String string) {
        Objects.requireNonNull(string);

        Matcher matcher = LETTERS_PATTERN.matcher(string);
        boolean prefixedByLetters = matcher.find();
        if (!prefixedByLetters) {
            throw new IllegalArgumentException("Horizontal position must be defined using letters");
        }
        int separatorIndex = matcher.end();
        int x = lexicalEncoder.decode(string.substring(0, separatorIndex));
        int y;
        try {
            y = Integer.parseInt(string.substring(separatorIndex)) - 1;
        } catch (Exception e) {
            throw new IllegalArgumentException("Vertical position must be defined as a positive integer", e);
        }

        return new Square(x, y);
    }
}
