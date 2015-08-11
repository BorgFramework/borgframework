package io.borgframework.interfaces;

import java.io.Serializable;
import java.util.Set;

public interface BorgJob extends Serializable {
    Set<BorgJob> getDependencies();
    void execute();

}
