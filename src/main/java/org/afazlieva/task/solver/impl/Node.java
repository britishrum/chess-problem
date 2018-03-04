package org.afazlieva.task.solver.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * A node of a directed graph
 */
public class Node<T> {
    private T value;
    private Node<T> parent;
    private Set<Node<T>> children = new HashSet<>();

    /**
     * Creates a root node
     */
    Node(T value) {
        this(null, value);
    }

    private Node(Node<T> parent, T value) {
        this.value = value;
        this.parent = parent;
    }

    void addChildValue(T child) {
        children.add(new Node<>(this, child));
    }

    void addAllChildValues(Collection<? extends T> children) {
        for (T child : children) {
            addChildValue(child);
        }
    }

    Set<Node<T>> getChildren() {
        return Collections.unmodifiableSet(children);
    }

    T getValue() {
        return value;
    }

    /**
     * @return parent node of the current node. For root node returns {@code null}.
     */
    Node<T> getParent() {
        return parent;
    }
}
