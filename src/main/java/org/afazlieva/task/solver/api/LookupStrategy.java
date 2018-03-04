package org.afazlieva.task.solver.api;

import java.util.Set;

/**
 * Defines strategy for finding related values based on the already existing value
 *
 * @param <T> type of value to be found
 */
public interface LookupStrategy<T> {

    Set<T> getNextValues(T current);

    boolean isValidValue(T next);
}
