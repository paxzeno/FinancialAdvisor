package com.mars_crater.dao;

import com.mars_crater.entities.TagEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Tags DAO.
 */
public class TagDAO {

    public List<TagEntity> getAllTags(EntityManager entityManager) {
        final TypedQuery getAllTags = entityManager.createNamedQuery(TagEntity.GET_ALL_TAGS, TagEntity.class);
        return getAllTags.getResultList();
    }
}
