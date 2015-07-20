package com.kritcg.elastic.dao;

import com.kritcg.elastic.entities.Blog;
import com.kritcg.elastic.repositories.BlogRepository;
import org.elasticsearch.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Chertpong on 7/17/2015.
 */
@Component
public class BlogDaoImpl implements BlogDao {
    @Autowired
    BlogRepository blogRepository;

    @Override
    public List<Blog> getBlogs() {
        if(blogRepository == null){
            System.out.println("REPOSITORY IS NULL");
        }
        return Lists.newArrayList(blogRepository.findAll());
    }

    @Override
    public List<Blog> getBlogs(String keyword) {
        return Lists.newArrayList(blogRepository.findByContentContaining(keyword));
    }

    @Override
    public Blog getBlog(String id) {
        return blogRepository.findOne(id);
    }

    @Override
    public Blog addBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public Blog updateBlog(Blog blog) {
        if(blogRepository.exists(blog.getId())){
            blogRepository.delete(blog.getId());
            blogRepository.save(blog);
            return blog;
        }
        else{
            return null;
        }
    }

    @Override
    public Blog deleteBlog(Blog blog) {
        if(blogRepository.exists(blog.getId())){
            blogRepository.delete(blog);
            return blog;
        }
        else{
            return null;
         }
    }
}
