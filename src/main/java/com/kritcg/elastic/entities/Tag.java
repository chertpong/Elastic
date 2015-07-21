package com.kritcg.elastic.entities;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * Created by Chertpong on 7/21/2015.
 */
public class Tag {
    @Field(
            type = FieldType.Integer,
            index = FieldIndex.not_analyzed
    )
    int id;
    @Field(
            type = FieldType.String,
            index = FieldIndex.not_analyzed
    )
    String name;

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

    @Override
    public String toString() {
        return "Tag{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
