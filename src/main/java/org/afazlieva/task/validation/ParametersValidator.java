package org.afazlieva.task.validation;

import org.afazlieva.task.solver.api.Parameters;

public interface ParametersValidator<T extends Parameters> {

    void validate(T parameters) throws ParametersValidationException;
}
