package com.mars_crater.data.Entities;

/**
 * Created by ateixeira on 01-02-2015.
 */
public class Tag {

    private String name;

    public Tag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Tag tag = (Tag) obj;

        if (name != null) {
            return compareTags(name, tag.name);
        }

        return true;
    }

    private boolean compareTags(String first, String second) {
        if (first.equals(second)) {
            return true;
        }

        if (first.length() > second.length()) {
            return first.contains(first);
        }

        return second.contains(first);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
