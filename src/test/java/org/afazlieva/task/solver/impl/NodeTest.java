package org.afazlieva.task.solver.impl;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    void testCreateRoot() {
        Object value = new Object();
        Node<Object> node = new Node<>(value);
        assertNull(node.getParent());
        assertEquals(value, node.getValue());
        assertNotNull(node.getChildren());
        assertTrue(node.getChildren().isEmpty());
    }

    @Test
    void testAddChild() {
        Node<Object> root = new Node<>(new Object());
        Object value = new Object();
        root.addChildValue(value);
        assertNotNull(root.getChildren());
        assertEquals(1, root.getChildren().size());
        Node<Object> child = Iterables.getOnlyElement(root.getChildren());
        assertNotNull(child);
        assertEquals(value, child.getValue());
        assertEquals(root, child.getParent());
        assertNotNull(child.getChildren());
        assertTrue(child.getChildren().isEmpty());
    }

    @Test
    void testAddChildren() {
        Node<Object> root = new Node<>(new Object());
        Set<Object> values = Sets.newHashSet(new Object(), new Object());
        root.addAllChildValues(values);
        assertNotNull(root.getChildren());
        assertEquals(values.size(), root.getChildren().size());
        Object childValues = root.getChildren().stream().map(Node::getValue).collect(Collectors.toSet());
        assertEquals(values, childValues);
        root.getChildren().forEach(child -> {
            assertEquals(root, child.getParent());
            assertTrue(child.getChildren().isEmpty());
            assertNotNull(child.getChildren());
        });
    }
}
