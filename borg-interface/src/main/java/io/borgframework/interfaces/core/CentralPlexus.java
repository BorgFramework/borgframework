package io.borgframework.interfaces.core;

import io.borgframework.interfaces.BorgService;

public interface CentralPlexus {

	<T extends BorgService> T getService(Class<T> serviceType);
}
