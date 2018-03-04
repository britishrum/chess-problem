package org.afazlieva.task.chess;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class KnightLookupStrategyTest {

    private KnightLookupStrategy strategy = new KnightLookupStrategy(8, 8);

    @Test
    void testCellWithinBoard() {
        assertFalse(strategy.isCellWithinBoard(-1, -1));
        assertFalse(strategy.isCellWithinBoard(-1, 0));
        assertFalse(strategy.isCellWithinBoard(0, -1));
        assertFalse(strategy.isCellWithinBoard(8, 8));
        assertFalse(strategy.isCellWithinBoard(8, 7));
        assertFalse(strategy.isCellWithinBoard(7, 8));
        assertTrue(strategy.isCellWithinBoard(0, 0));
        assertTrue(strategy.isCellWithinBoard(7, 7));
        assertTrue(strategy.isCellWithinBoard(5, 0));
        assertTrue(strategy.isCellWithinBoard(0, 5));
        assertTrue(strategy.isCellWithinBoard(5, 5));
    }

    @Test
    void testNextNodesEmpty() {
        assertTrue(strategy.getNextValues(new Square(10, 10)).isEmpty());
        assertTrue(strategy.getNextValues(new Square(0, 10)).isEmpty());
        assertTrue(strategy.getNextValues(new Square(10, 0)).isEmpty());
        assertTrue(strategy.getNextValues(new Square(-5, -5)).isEmpty());
        assertTrue(strategy.getNextValues(new Square(0, -5)).isEmpty());
        assertTrue(strategy.getNextValues(new Square(-5, 0)).isEmpty());
    }

    @ParameterizedTest(name = "{0}")
    @ArgumentsSource(NextNodeArgumentsProvider.class)
    void testNextNodes(String displayName, Square current, Set<Square> expected) {
        Set<Square> actual = strategy.getNextValues(current);
        assertFalse(actual.isEmpty());
        assertEquals(expected, actual);
    }

    private static class NextNodeArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    Arguments.of("Top-left corner",
                            new Square(0, 0),
                            Sets.newHashSet(new Square(1, 2), new Square(2, 1))),
                    Arguments.of("Bottom-right corner",
                            new Square(7, 7),
                            Sets.newHashSet(new Square(5, 6), new Square(6, 5))),
                    Arguments.of("Bottom-left corner",
                            new Square(0, 7),
                            Sets.newHashSet(new Square(1, 5), new Square(2, 6))),
                    Arguments.of("Top-right corner",
                            new Square(7, 0),
                            Sets.newHashSet(new Square(6, 2), new Square(5, 1))),
                    Arguments.of("Middle",
                            new Square(4, 4),
                            Sets.newHashSet(new Square(5, 6), new Square(5, 2),
                                    new Square(3, 2), new Square(3, 6),
                                    new Square(6, 3), new Square(6, 5),
                                    new Square(2, 3), new Square(2, 5)))
            );
        }
    }
}
