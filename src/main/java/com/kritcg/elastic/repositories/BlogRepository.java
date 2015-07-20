package com.kritcg.elastic.repositories;

import com.kritcg.elastic.entities.Blog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * Created by Chertpong on 7/17/2015.
 */

public interface BlogRepository extends ElasticsearchRepository<Blog, String> {
    List<Blog> findByContentContaining(String keyword);
}
