package com.kritcg.elastic.dao;

import com.kritcg.elastic.entities.Blog;
import com.kritcg.elastic.entities.Tag;
import com.kritcg.elastic.repositories.BlogRepository;
import org.elasticsearch.common.collect.Lists;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chertpong on 7/17/2015.
 */
@Component
public class BlogDaoImpl implements BlogDao {
    @Autowired
    BlogRepository blogRepository;
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;
    @Override
    public List<Blog> getBlogs() {
        if(blogRepository == null){
            System.out.println("REPOSITORY IS NULL");
        }
        return Lists.newArrayList(blogRepository.findAll());
    }

    @Override
    public List<Blog> getBlogs(String keyword) {
        return Lists.newArrayList(blogRepository.findByContentLike(keyword));
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

    @Override
    public List<Blog> getByTags(List<Tag> tagList) {
//        SearchQuery query = new NativeSearchQueryBuilder()
//                .withQuery(
//                        QueryBuilders.filteredQuery(
//                                QueryBuilders.matchAllQuery(),
//                                FilterBuilders.termsFilter("id", "1")
//                        )
//                )
//                .build();
//        List<Blog> list = new ArrayList<Blog>();
//        blogRepository.search(query).forEach(b->list.add(b));
        return null;
    }

    @Override
    public List<Blog> getByTitleAndContentLike(String keyword) {
        SearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(
                        QueryBuilders.multiMatchQuery(keyword, "title", "content")
                                .type(MultiMatchQueryBuilder.Type.BEST_FIELDS)
                )
                .build();
        return elasticsearchTemplate.queryForList(query,Blog.class);
    }
}
