package com.kritcg.elastic;

import com.kritcg.elastic.entities.Blog;
import com.kritcg.elastic.repositories.BlogRepository;
import org.elasticsearch.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ApplicationTests {
	@Autowired
	BlogRepository blogRepository;

	@Test
	public void contextLoads() {
	}

	@Test

	public void shouldGetAllBlogs(){
		List<Blog> blogList = Lists.newArrayList(blogRepository.findAll());
		blogList.forEach(b-> System.out.println(b.toString()));
	}

}
