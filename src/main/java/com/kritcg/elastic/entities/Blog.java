package com.kritcg.elastic.entities;

import org.elasticsearch.common.Nullable;
import org.elasticsearch.index.mapper.core.CompletionFieldMapper;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by Chertpong on 7/17/2015.
 */
@Document( indexName = "blogs",type = "blog")
public class Blog {
    @Id
    private String id;

    @Field(
            type = FieldType.String,
            index = FieldIndex.analyzed,
            searchAnalyzer = "thai",
            indexAnalyzer = "thai",
            store = true
    )
    private String title;

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

    @Field(
            type = FieldType.String,
            index = FieldIndex.not_analyzed,
            store = true
    )
    private String url;

    @Field(
            type = FieldType.Nested
    )
    private List<Tag> tags;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", createDate=" + createDate +
                ", content='" + content + '\'' +
                ", url='" + url + '\'' +
                ", tags=" + tags +
                '}';
    }
}

