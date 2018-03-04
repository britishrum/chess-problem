package org.afazlieva.task.view;

import org.afazlieva.task.solver.api.Parameters;

public interface ParametersParser<T> {
    Parameters<T> parse(String[] args);
}
