package io.borgframework.interfaces.core;

import io.borgframework.interfaces.BorgEndpoint;
import io.borgframework.interfaces.BorgEvent;
import io.borgframework.interfaces.BorgJob;
import io.borgframework.interfaces.BorgJobId;
import io.borgframework.interfaces.BorgListener;
import io.borgframework.interfaces.BorgModule;
import io.borgframework.interfaces.BorgService;

public interface CentralPlexus {

	<T extends BorgService> T getService(Class<T> serviceType);

	<T extends BorgEvent> void registerEventListener(BorgModule module, BorgListener<T> listener, Class<T> clazz);

	<T extends BorgEvent> void unregisterEventListener(BorgModule module, BorgListener<T> listener, Class<T> clazz);

	void fireEvent(BorgEvent event);

	void registerService(BorgModule module, BorgService service);

	void unregisterService(BorgModule module, BorgService service);

	void registerEndpoint(BorgModule module, BorgEndpoint endpoint);

	void unregisterEndpoint(BorgModule module, BorgEndpoint endpoint);

	BorgJobId submitJob(BorgJob job);
}
