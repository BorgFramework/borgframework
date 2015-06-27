package io.borgframework;

import io.borgframework.interfaces.BorgService;
import io.borgframework.interfaces.core.CentralPlexus;

public final class Borg {
	
	private static CentralPlexus plexus;

	private Borg() {}
	
	public static <T extends BorgService> T getService(Class<T> serviceType) {
		return plexus.getService(serviceType);
	}
	
	
}
