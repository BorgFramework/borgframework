package io.borgframework;

import io.borgframework.interfaces.BorgEndpoint;
import io.borgframework.interfaces.BorgEvent;
import io.borgframework.interfaces.BorgJob;
import io.borgframework.interfaces.BorgJobId;
import io.borgframework.interfaces.BorgListener;
import io.borgframework.interfaces.BorgModule;
import io.borgframework.interfaces.BorgService;
import io.borgframework.interfaces.core.CentralPlexus;

public final class Borg {
	
	private static CentralPlexus plexus;

	private Borg() {}

	public static void registerService(BorgModule module, BorgService service) {
		plexus.registerService(module, service);
	}

	public static void unregisterService(BorgModule module, BorgService service) {
		plexus.unregisterService(module, service);
	}
	
	public static <T extends BorgService> T getService(Class<T> serviceType) {
		return plexus.getService(serviceType);
	}

	public static void registerEndpoint(BorgModule module, BorgEndpoint endpoint) {
		plexus.registerEndpoint(module, endpoint);
	}

	public static void unregisterEndpoint(BorgModule module, BorgEndpoint endpoint) {
		plexus.unregisterEndpoint(module, endpoint);
	}

	public static <T extends BorgEvent> void registerEventListener(BorgModule module, BorgListener<T> listener, Class<T> clazz) {
		plexus.registerEventListener(module, listener, clazz);
	}

	public static <T extends BorgEvent> void unregisterEventListener(BorgModule module, BorgListener<T> listener, Class<T> clazz) {
		plexus.unregisterEventListener(module, listener, clazz);
	}

	public static void fireEvent(BorgEvent event) {
		plexus.fireEvent(event);
	}

	public static BorgJobId submitJob(BorgJob job) {
		return plexus.submitJob(job);
	}
}
