package com.mars_crater.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Tag information used to categorize each transaction.
 */
@Entity
@Table(name = "T_TAG")
@NamedQueries(
        @NamedQuery(name = TagEntity.GET_ALL_TAGS, query = "SELECT t FROM TagEntity t")
)
public class TagEntity implements Serializable {

    private static final long serialVersionUID = -7307445259445513388L;

    public static final String GET_ALL_TAGS = "TagEntity.GetAllTags";

    public TagEntity() {
    }

    public TagEntity(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TAG_ID")
    private int id;

    @Column(name = "TAG_NAME")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
