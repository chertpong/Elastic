package com.kritcg.elastic;

import com.kritcg.elastic.entities.Blog;
import com.kritcg.elastic.services.SearchBlogService;
import com.kritcg.elastic.services.SearchBlogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.web.WebApplicationInitializer;

import java.util.Date;

@SpringBootApplication

public class Application extends SpringBootServletInitializer implements CommandLineRunner{

    private SearchBlogServiceImpl searchBlogService = new SearchBlogServiceImpl();
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        //can run something here
    }
    //If you want to build jar file, just comment this and bring out SpringBootServletInitializer
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }
}
