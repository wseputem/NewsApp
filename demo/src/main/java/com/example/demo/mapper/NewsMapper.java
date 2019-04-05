package com.example.demo.mapper;

import com.example.demo.dto.NewsDTO;
import com.example.demo.persistence.entity.Category;
import com.example.demo.persistence.entity.News;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NewsMapper {
    private CategoryService categoryService;
    private CategoryMapper categoryMapper;

    @Autowired
    public NewsMapper(CategoryService categoryService,
                      CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    public News createNews(NewsDTO newsDTO) {
        Category category = categoryMapper.createCategory(categoryService.findById(newsDTO.getCategoryId()));
        News news = new News();
        news.setId(newsDTO.getId());
        news.setHeader(newsDTO.getHeader());
        news.setCategory(category);
        news.setId(news.getId());
        news.setBody(newsDTO.getBody());
        return news;
    }

    public NewsDTO createNewsDTO(News news) {
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setBody(news.getBody());
        newsDTO.setHeader(news.getHeader());
        newsDTO.setCategoryId(news.getCategory().getId());
        newsDTO.setId(news.getId());
        return newsDTO;
    }

    public List<News> createNewsList(List<NewsDTO> newsDTOS) {
        List<News> news = new ArrayList<>();
        for (NewsDTO newsDTO : newsDTOS) {
             news.add(createNews(newsDTO));
        }
        return news;
    }

    public List<NewsDTO> createNewsDTOList(List<News> newsList) {
        List<NewsDTO> newsDTOS = new ArrayList<>();
        for (News news : newsList) {
            newsDTOS.add(createNewsDTO(news));
        }
        return newsDTOS;
    }

}
