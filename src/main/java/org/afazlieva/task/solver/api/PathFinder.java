package org.afazlieva.task.solver.api;

import java.util.Collection;
import java.util.List;

public interface PathFinder<T> {

    Collection<List<T>> findPaths(T start, T end, int maxLength);
}
