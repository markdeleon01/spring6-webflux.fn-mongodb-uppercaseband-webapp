package com.uppercaseband.services;

import com.uppercaseband.domain.Category;
import com.uppercaseband.mappers.ArticleMapper;
import com.uppercaseband.mappers.MediaMapper;
import com.uppercaseband.model.ArticleDTO;
import com.uppercaseband.repositories.ArticleRepository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import reactor.core.publisher.Flux;



@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class ArticleServiceImplTest {	//unit tests the service and mappers

    ArticleService articleService;

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    ArticleMapper articleMapper;    // use the mapper from the Spring context


    @BeforeAll
    public void setUp() {
        articleService = new ArticleServiceImpl(articleRepository, articleMapper);

        assertNotNull(articleRepository);
        assertNotNull(articleMapper);
        assertNotNull(ArticleMapper.INSTANCE);
        assertNotNull(MediaMapper.INSTANCE);
    }


    @Test
    public void testGetAllArticles() {

        Flux<ArticleDTO> articles = articleService.getAllArticles();
        Long count = articles.count().block();	//trigger the service call and conversions
        assertEquals(3, count);
    }


    @Test
    public void getArticlesByCategory() {

        Flux<ArticleDTO> articles = articleService.getArticlesByCategory(Category.EVENTS.name());
        Long count = articles.count().block();	//trigger the service call and conversions
        assertEquals(1, count);
    }

    @Test
    public void getArticlesByInvalidCategory() {

        Flux<ArticleDTO> articles = articleService.getArticlesByCategory("INVALID");
        Long count = articles.count().block();	//trigger the service call and conversions
        assertEquals(0, count);
    }
}
