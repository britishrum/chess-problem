package org.afazlieva.task.solver.api;

public interface Parameters<T> {

    T getStart();

    T getEnd();

    int getMaxPathLength();

}
