package io.borgframework.core;

import io.borgframework.core.io.borgframework.core.job.BorgJobManager;
import io.borgframework.interfaces.BorgEndpoint;
import io.borgframework.interfaces.BorgEvent;
import io.borgframework.interfaces.BorgJob;
import io.borgframework.interfaces.BorgJobId;
import io.borgframework.interfaces.BorgListener;
import io.borgframework.interfaces.BorgModule;
import io.borgframework.interfaces.BorgService;
import io.borgframework.interfaces.core.CentralPlexus;

public class CentralPlexusImpl implements CentralPlexus {
    private final BorgServiceRegistry serviceRegistry;
    private final BorgEventRegistry eventRegistry;
    private final BorgEndpointRegistry endpointRegistry;
    private final BorgJobManager jobManager;

    protected CentralPlexusImpl(BorgServiceRegistry serviceRegistry, BorgEventRegistry eventRegistry, BorgEndpointRegistry endpointRegistry, BorgJobManager jobManager) {
        this.serviceRegistry = serviceRegistry;
        this.eventRegistry = eventRegistry;
        this.endpointRegistry = endpointRegistry;
        this.jobManager = jobManager;
    }

    @Override
    public <T extends BorgService> T getService(Class<T> serviceType) {
        return (T) serviceRegistry.get(serviceType);
    }

    @Override
    public <T extends BorgEvent> void registerEventListener(BorgModule module, BorgListener<T> listener, Class<T> clazz) {
        eventRegistry.register(module, clazz, listener);
    }

    @Override
    public <T extends BorgEvent> void unregisterEventListener(BorgModule module, BorgListener<T> listener, Class<T> clazz) {
        eventRegistry.unregister(module, clazz, listener);
    }

    @Override
    public void fireEvent(BorgEvent event) {
        for (BorgListener listener : eventRegistry.getAll(event.getClass())) {
            listener.handle(event);
        }
    }

    @Override
    public void registerService(BorgModule module, BorgService service) {
        serviceRegistry.register(module, service.getClass(), service);
    }

    @Override
    public void unregisterService(BorgModule module, BorgService service) {
        serviceRegistry.unregister(module, service.getClass(), service);
    }

    @Override
    public void registerEndpoint(BorgModule module, BorgEndpoint endpoint) {
        endpointRegistry.register(module, endpoint.getPathPrefix(), endpoint);
    }

    @Override
    public void unregisterEndpoint(BorgModule module, BorgEndpoint endpoint) {
        endpointRegistry.unregister(module, endpoint.getPathPrefix(), endpoint);
    }

    @Override
    public BorgJobId submitJob(BorgJob job) {
        return jobManager.submit(job);
    }
}
