package com.mars_crater.data.cache;

import com.mars_crater.entities.TagEntity;

/**
 * Tag cache object.
 */
public class TagCache extends AbstractCache<TagEntity, String> {

    public void addEntity(TagEntity entity) {
        this.getCacheMap().put(entity.getName(), entity);
    }
}
