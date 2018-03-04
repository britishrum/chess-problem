package org.afazlieva.task.solver.impl;

import org.afazlieva.task.solver.api.LookupStrategy;
import org.afazlieva.task.solver.api.PathFinder;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Finds all shortest paths from starting to ending position.
 * Uses BFS algorithm to discover new nodes and check if the ending position has been reached.
 */
public class ShortestPathFinder<T> implements PathFinder<T> {

    private final LookupStrategy<T> lookupStrategy;

    public ShortestPathFinder(LookupStrategy<T> lookupStrategy) {
        Objects.requireNonNull(lookupStrategy);
        this.lookupStrategy = lookupStrategy;
    }

    /**
     * Finds all shortest paths from starting to ending position.
     * Uses BFS algorithm to discover new nodes and check if the ending position has been reached.
     *
     * @param start     starting position
     * @param end       ending position
     * @param maxLength maximum length of the paths
     * @return a collection of path from starting (inclusive) to ending (inclusive) positions
     */
    public Collection<List<T>> findPaths(final T start, final T end, final int maxLength) {
        if (maxLength < 0 || !lookupStrategy.isValidValue(start) || !lookupStrategy.isValidValue(end)) {
            throw new IllegalArgumentException();
        }
        List<Node<T>> result = Collections.emptyList();

        Collection<Node<T>> children = Collections.singletonList(new Node<>(start));
        for (int i = 0; i <= maxLength; i++) {
            result = children.stream().filter(child -> isEndNode(child, end)).collect(Collectors.toList());
            if (!result.isEmpty()) {
                break;
            }
            children = discoverChildren(children, lookupStrategy);
        }
        return result.stream().map(this::getPath).collect(Collectors.toList());
    }

    /**
     * Discovers and returns children for all provided nodes
     */
    private List<Node<T>> discoverChildren(Collection<Node<T>> nodes, LookupStrategy<T> lookupStrategy) {
        return nodes.stream()
                .flatMap(node -> discoverChildren(node, lookupStrategy).stream())
                .collect(Collectors.toList());
    }

    /**
     * Discovers and returns children for a provided node
     */
    private Collection<Node<T>> discoverChildren(Node<T> node, LookupStrategy<T> lookupStrategy) {
        T value = node.getValue();
        Set<T> next = lookupStrategy.getNextValues(value);
        node.addAllChildValues(next);
        return node.getChildren();
    }

    private boolean isEndNode(Node<T> node, T endValue) {
        return Objects.equals(node.getValue(), endValue);
    }

    /**
     * Returns a list of all parent nodes, starting from the root (inclusive) to the given node (inclusive)
     */
    private List<T> getPath(Node<T> leaf) {
        LinkedList<T> result = new LinkedList<>();
        while (leaf != null) {
            result.addFirst(leaf.getValue());
            leaf = leaf.getParent();
        }
        return result;
    }
}
