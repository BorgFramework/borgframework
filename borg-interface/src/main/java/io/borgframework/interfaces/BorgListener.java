package io.borgframework.interfaces;

import java.io.Serializable;

public interface BorgListener<T extends BorgEvent> extends Serializable {
    void handle(T event);
}
