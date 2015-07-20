package com.kritcg.elastic.services;

import com.kritcg.elastic.dao.BlogDao;
import com.kritcg.elastic.dao.BlogDaoImpl;
import com.kritcg.elastic.entities.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Chertpong on 7/17/2015.
 */
@Service
public class SearchBlogServiceImpl implements SearchBlogService{
    @Autowired
    private BlogDao blogDao;

    @Override
    public List<Blog> getBlogs() {
        return blogDao.getBlogs();
    }

    @Override
    public List<Blog> getBlogs(String keyword) {
        return blogDao.getBlogs(keyword);
    }

    @Override
    public Blog getBlog(String id) {
        return blogDao.getBlog(id);
    }

    @Override
    public Blog addBlog(Blog blog) {
        return blogDao.addBlog(blog);
    }

    @Override
    public Blog updateBlog(Blog blog) {
        return blogDao.updateBlog(blog);
    }

    @Override
    public Blog deleteBlog(Blog blog) {
        return blogDao.deleteBlog(blog);
    }

    public Blog deleteBlog(String id){
        Blog b = new Blog();
        b.setId(id);
        return blogDao.deleteBlog(b);
    }
}
