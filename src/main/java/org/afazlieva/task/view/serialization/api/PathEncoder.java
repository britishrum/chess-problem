package org.afazlieva.task.view.serialization.api;

import java.util.List;

public interface PathEncoder<T> {

    String encode(List<T> path);
}
