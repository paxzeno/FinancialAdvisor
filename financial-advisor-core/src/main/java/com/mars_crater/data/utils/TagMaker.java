package com.mars_crater.data.utils;

import com.mars_crater.BDUtils;
import com.mars_crater.data.Entities.Tag;
import com.mars_crater.entities.TagEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * This class has the intelligence behind making the tags.
 */
public class TagMaker {

    private static Map<Tag, Integer> tagCache;

    public TagMaker() {
        tagCache = new HashMap<>();
    }

    public void lookingForTags(String transactionDescription) {
        final String[] rawTags = transactionDescription.split(" ");

        for (String rawTag : rawTags) {
            if (rawTag.length() > 1 && !tagCache.containsKey(new Tag(rawTag))) {
                final TagEntity tagEntity = new TagEntity();
                tagEntity.setName(rawTag);
                BDUtils.insertTag(tagEntity);
                tagCache.put(new Tag(rawTag), 1);
            }
        }
    }

    public static Map<Tag, Integer> getTagCache() {
        return tagCache;
    }
}
