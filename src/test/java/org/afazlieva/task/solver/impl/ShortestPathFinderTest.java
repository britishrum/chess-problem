package org.afazlieva.task.solver.impl;

import com.example.mockito.MockitoExtension;
import com.google.common.collect.Sets;
import org.afazlieva.task.chess.Square;
import org.afazlieva.task.solver.api.LookupStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShortestPathFinderTest {

    private ShortestPathFinder<Square> pathFinder;

    @Mock
    private LookupStrategy<Square> lookupStrategy;

    @BeforeEach
    void setUp() {
        pathFinder = new ShortestPathFinder<>(lookupStrategy);
    }

    @Test
    void testFindOnePath() {
        LinkedList<Square> shortest = new LinkedList<>(Arrays.asList(
                new Square(12, 12),
                new Square(22, 22),
                new Square(0, 1)
        ));
        when(lookupStrategy.isValidValue(any())).thenReturn(true);
        for (int i = 0; i < shortest.size() - 1; i++) {
            when(lookupStrategy.getNextValues(eq(shortest.get(i)))).thenReturn(Collections.singleton(shortest.get(i + 1)));
        }

        Collection<List<Square>> paths = pathFinder.findPaths(shortest.getFirst(), shortest.getLast(), 3);
        assertEquals(1, paths.size());
        assertTrue(paths.contains(shortest));
    }

    @Test
    void testFindTwoPath() {
        LinkedList<Square> firstPath = new LinkedList<>(Arrays.asList(
                new Square(12, 12),
                new Square(22, 22),
                new Square(0, 1)
        ));
        LinkedList<Square> secondPath = new LinkedList<>(Arrays.asList(
                firstPath.getFirst(),
                new Square(100, 100),
                firstPath.getLast()
        ));
        when(lookupStrategy.isValidValue(any())).thenReturn(true);
        when(lookupStrategy.getNextValues(eq(firstPath.get(0)))).thenReturn(Sets.newHashSet(firstPath.get(1), secondPath.get(1)));
        when(lookupStrategy.getNextValues(eq(firstPath.get(1)))).thenReturn(Collections.singleton(firstPath.get(2)));
        when(lookupStrategy.getNextValues(eq(secondPath.get(1)))).thenReturn(Collections.singleton(secondPath.get(2)));

        Collection<List<Square>> actualPaths = pathFinder.findPaths(firstPath.getFirst(), firstPath.getLast(), 3);
        assertEquals(2, actualPaths.size());
        assertTrue(actualPaths.contains(firstPath));
        assertTrue(actualPaths.contains(secondPath));
    }

    @Test
    void testFindNoPathDueToMaxLength() {
        LinkedList<Square> shortest = new LinkedList<>(Arrays.asList(
                new Square(12, 12),
                new Square(22, 22),
                new Square(0, 1),
                new Square(5, 18)
        ));
        when(lookupStrategy.isValidValue(any())).thenReturn(true);
        for (int i = 0; i < shortest.size() - 1; i++) {
            when(lookupStrategy.getNextValues(eq(shortest.get(i)))).thenReturn(Collections.singleton(shortest.get(i + 1)));
        }

        Collection<List<Square>> paths = pathFinder.findPaths(shortest.getFirst(), shortest.getLast(), 2);
        assertTrue(paths.isEmpty());
    }

    @Test
    void testStartEqualsEnd() {
        LinkedList<Square> shortest = new LinkedList<>(Collections.singletonList(new Square(12, 12)));
        when(lookupStrategy.isValidValue(any())).thenReturn(true);
        when(lookupStrategy.getNextValues(eq(shortest.get(0)))).thenReturn(Collections.emptySet());
        Collection<List<Square>> actualPaths = pathFinder.findPaths(shortest.getFirst(), shortest.getLast(), 0);
        assertEquals(1, actualPaths.size());
        assertTrue(actualPaths.contains(shortest));
    }

    @Test
    void testFindNoPathDueToEndingPositionNotReachable() {
        LinkedList<Square> shortest = new LinkedList<>(Arrays.asList(
                new Square(12, 12),
                new Square(22, 22),
                new Square(0, 1)
        ));
        when(lookupStrategy.isValidValue(any())).thenReturn(true);
        for (int i = 0; i < shortest.size() - 1; i++) {
            when(lookupStrategy.getNextValues(eq(shortest.get(i)))).thenReturn(Collections.singleton(shortest.get(i + 1)));
        }

        Collection<List<Square>> paths = pathFinder.findPaths(shortest.getFirst(), new Square(5, 18), 10);
        assertTrue(paths.isEmpty());
    }
}
