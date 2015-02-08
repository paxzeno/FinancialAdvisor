package com.mars_crater.data.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ateixeira on 01-02-2015.
 */
public abstract class AbstractCache<T, K> implements Cache<T, K> {

    private Map<K, T> cacheMap = new HashMap<>();

    @Override
    public void addEntityList(List<T> entityList) {
        for (T t : entityList) {
            this.addEntity(t);
        }
    }

    @Override
    public T getEntityByKey(K key) {
        return this.cacheMap.get(key);
    }

    public Map<K, T> getCacheMap() {
        return this.cacheMap;
    }
}
