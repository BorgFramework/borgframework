package io.borgframework.interfaces;

import java.io.Serializable;

public interface BorgModule extends Serializable {
    void setLifecycleStatus(BorgLifecycleStatus status);
}
