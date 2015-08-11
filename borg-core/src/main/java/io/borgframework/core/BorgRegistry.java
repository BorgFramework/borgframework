package io.borgframework.core;

import io.borgframework.interfaces.BorgModule;

import java.util.HashMap;
import java.util.Map;

public class BorgRegistry<K, V> {
    public Map<K, V> map = new HashMap<>();
    public Map<BorgModule, Map<K, V>> owners = new HashMap<>();

    public V get(K key) {
        synchronized (this) {
            return map.get(key);
        }
    }

    public void register(BorgModule module, K key, V value) {
        synchronized (this) {
            map.put(key, value);
            if (owners.containsKey(module)) {
                owners.put(module, new HashMap<>());
            }
            owners.get(module).put(key, value);
        }
    }

    public void unregister(BorgModule module, K key, V value) {
        synchronized (this) {
            map.remove(key);
            if (owners.containsKey(module)) {
                owners.get(module).remove(key);
                if (owners.get(module).isEmpty()) {
                    owners.remove(module);
                }
            }
        }
    }

    public void unregisterAll(BorgModule module) {
        synchronized (this) {
            if (owners.containsKey(module)) {
                for (Map.Entry<K, V> entry : new HashMap<>(owners.get(module)).entrySet()) {
                    unregister(module, entry.getKey(), entry.getValue());
                }
            }
        }
    }
}
