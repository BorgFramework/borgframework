package io.borgframework.core;

import com.google.common.collect.ImmutableSet;
import io.borgframework.interfaces.BorgModule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BorgMultiRegistry<K, V> {
    public Map<K, Set<V>> map = new HashMap<>();
    public Map<BorgModule, Map<K, Set<V>>> owners = new HashMap<>();

    public V get(K key) {
        synchronized (this) {
            if (map.containsKey(key) && map.get(key).iterator().hasNext()) {
                return map.get(key).iterator().next();
            } else {
                return null;
            }
        }
    }

    public Set<V> getAll(K key) {
        synchronized (this) {
            return ImmutableSet.copyOf(map.get(key));
        }
    }

    public void register(BorgModule module, K key, V value) {
        synchronized (this) {
            if (map.containsKey(key)) {
                map.put(key, new HashSet<>());
            }
            map.get(key).add(value);
            if (owners.containsKey(module)) {
                owners.put(module, new HashMap<>());
            }
            if (owners.get(module).containsKey(key)) {
                owners.get(module).put(key, new HashSet<>());
            }
            owners.get(module).get(key).add(value);
        }
    }

    public void unregister(BorgModule module, K key, V value) {
        synchronized (this) {
            if (map.containsKey(key)) {
                map.get(key).remove(value);
                if (map.get(key).isEmpty()) {
                    map.remove(key);
                }
            }
            if (owners.containsKey(module)) {
                if (owners.get(module).containsKey(key)) {
                    owners.get(module).get(key).remove(value);
                    if (owners.get(module).get(key).isEmpty()) {
                        owners.get(module).remove(key);
                    }
                }
                if (owners.get(module).isEmpty()) {
                    owners.remove(module);
                }
            }
        }
    }

    public void unregisterAll(BorgModule module) {
        synchronized (this) {
            if (owners.containsKey(module)) {
                for (Map.Entry<K, Set<V>> entry : new HashMap<>(owners.get(module)).entrySet()) {
                    for (V value : entry.getValue()) {
                        unregister(module, entry.getKey(), value);
                    }
                }
            }
        }
    }
}
