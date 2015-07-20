package com.kritcg.elastic.entities;

import org.elasticsearch.common.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Chertpong on 7/17/2015.
 */
@Document( indexName = "blog",type = "blog")
public class Blog {
    @Id
    private String id;

    @Field(
            type = FieldType.Date,
            index = FieldIndex.not_analyzed,
            store = true,
            format = DateFormat.custom, pattern = "yyyy.MM.dd hh:mm")
    private Date createDate;

    @NotNull
    @Field(
            type = FieldType.String,
            index = FieldIndex.analyzed,
            searchAnalyzer = "thai",
            indexAnalyzer = "thai",
            store = true
    )
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id='" + id + '\'' +
                ", createDate=" + createDate +
                ", content='" + content + '\'' +
                '}';
    }
}

