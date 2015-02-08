package com.mars_crater.data.utils;

import com.mars_crater.BDUtils;
import com.mars_crater.entities.TagEntity;

import java.util.Map;

/**
 * This class has the intelligence behind making the tags.
 */
public class TagMaker {

    public static void lookingForTags(Map<String, TagEntity> tagCache, String transactionDescription) {
        final String[] rawTags = transactionDescription.split(" ");

        for (String rawTag : rawTags) {
            if (rawTag.length() > 1 && !tagCache.containsKey(rawTag)) {
                final TagEntity tagEntity = new TagEntity();
                tagEntity.setName(rawTag);
                BDUtils.insertTag(tagEntity);
                tagCache.put(rawTag, new TagEntity(rawTag));
            }
        }
    }
}
