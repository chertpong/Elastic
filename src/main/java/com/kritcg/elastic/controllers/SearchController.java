package com.kritcg.elastic.controllers;

import com.kritcg.elastic.entities.Blog;
import com.kritcg.elastic.services.SearchBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Chertpong on 7/17/2015.
 */

@RestController
@RequestMapping("search")
public class SearchController {
    @Autowired
    private SearchBlogService searchBlogService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public List<Blog> getBlogs(){
        return searchBlogService.getBlogs();
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public Blog getBlog(@PathVariable String id){
        return searchBlogService.getBlog(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Blog addBlog(Blog blog){
        return searchBlogService.addBlog(blog);
    }

    @RequestMapping(value = "/delete/{id}")
    public Blog deleteBlog(@PathVariable String id){
        return searchBlogService.deleteBlog(id);
    }

}
