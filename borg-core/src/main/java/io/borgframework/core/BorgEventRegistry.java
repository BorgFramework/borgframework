package io.borgframework.core;

import io.borgframework.interfaces.BorgEvent;
import io.borgframework.interfaces.BorgListener;

public class BorgEventRegistry extends BorgMultiRegistry<Class<? extends BorgEvent>, BorgListener<? extends BorgEvent>> {
}
