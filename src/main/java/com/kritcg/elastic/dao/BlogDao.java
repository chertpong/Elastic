package com.kritcg.elastic.dao;

import com.kritcg.elastic.entities.Blog;
import com.kritcg.elastic.entities.Tag;

import java.util.List;

/**
 * Created by Chertpong on 7/17/2015.
 */
public interface BlogDao {
    List<Blog> getBlogs();
    List<Blog> getBlogs(String keyword);
    Blog getBlog(String id);
    Blog addBlog(Blog blog);
    Blog updateBlog(Blog blog);
    Blog deleteBlog(Blog blog);
    List<Blog> getByTags(List<Tag> tagList);
    List<Blog> getByTitleAndContentLike(String keyword);
}
