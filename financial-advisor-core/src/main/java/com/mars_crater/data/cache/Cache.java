package com.mars_crater.data.cache;

import java.util.List;

/**
 * Created by ateixeira on 01-02-2015.
 */
public interface Cache<T, K> {

    public void addEntityList(List<T> entityList);

    public void addEntity(T entity);

    public T getEntityByKey(K key);
}
