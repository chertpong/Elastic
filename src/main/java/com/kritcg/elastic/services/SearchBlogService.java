package com.kritcg.elastic.services;

import com.kritcg.elastic.entities.Blog;

import java.util.List;

/**
 * Created by Chertpong on 7/17/2015.
 */
public interface SearchBlogService {
    List<Blog> getBlogs();
    List<Blog> getBlogs(String keyword);
    Blog getBlog(String id);
    Blog addBlog(Blog blog);
    Blog updateBlog(Blog blog);
    Blog deleteBlog(Blog blog);
    Blog deleteBlog(String id);
}
