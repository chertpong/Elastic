package com.kritcg.elastic.controllers;

import com.kritcg.elastic.entities.Blog;
import com.kritcg.elastic.services.SearchBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Chertpong on 7/17/2015.
 */

@RestController
@RequestMapping("/")
public class SearchController {
    @Autowired
    private SearchBlogService searchBlogService;

    @ResponseBody
    @RequestMapping(value = "search/all",method = RequestMethod.GET)
    public List<Blog> getBlogs(){ return searchBlogService.getBlogs(); }

    @RequestMapping(value = "search/id/{id}", method = RequestMethod.GET)
    public Blog getBlog(@PathVariable String id){
        return searchBlogService.getBlog(id);
    }

    @RequestMapping(value = "search/add", method = RequestMethod.POST)
    public Blog addBlog(@RequestBody Blog blog, BindingResult bindingResult){
        return searchBlogService.addBlog(blog);
    }

    @RequestMapping(value = "search/delete/{id}", method = RequestMethod.GET)
    public Blog deleteBlog(@PathVariable String id){
        return searchBlogService.deleteBlog(id);
    }

    @RequestMapping(value = "search/update", method = RequestMethod.PUT)
    public Blog updateBlog(@RequestBody Blog blog, BindingResult bindingResult){ return searchBlogService.updateBlog(blog); }

    @ResponseBody
    @RequestMapping(value = "search/keywords/{keyword}", method = RequestMethod.GET)
    public List<Blog> getBlogs(@PathVariable String keyword){
        return searchBlogService.getBlogs(keyword);
    }
}
