package com.example.demo.service;

import com.example.demo.dto.NewsDTO;
import com.example.demo.dto.CategoryDTO;
import com.example.demo.mapper.NewsMapper;
import com.example.demo.persistence.entity.Category;
import com.example.demo.persistence.entity.News;
import com.example.demo.persistence.repository.NewsRepository;
import com.example.demo.persistence.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class NewsService {
    private NewsRepository newsRepository;
    private NewsMapper newsMapper;
    private CategoryRepository categoryRepository;

    @Autowired
    public NewsService(NewsRepository newsRepository, NewsMapper newsMapper, CategoryRepository categoryRepository) {
        this.newsRepository = newsRepository;
        this.newsMapper = newsMapper;
        this.categoryRepository = categoryRepository;
    }

    public NewsDTO save(NewsDTO newsDTO) {
        News news = newsMapper.createNews(newsDTO);
        news = newsRepository.save(news);
        return newsMapper.createNewsDTO(news);
    }

    public String delete(Long id) {
        News news = newsRepository.findById(id).orElse(null);
        if (news != null) {
            newsRepository.delete(news);
            return "success";
        } else {
            return "failure";
        }

    }

    public NewsDTO update(NewsDTO newsDTO) {
        if (newsRepository.existsById(newsDTO.getId())) {
            return save(newsDTO);
        } else {
            return null;
        }
    }

    public Iterable<News> searchByCategory(CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(categoryDTO.getId()).orElse(null);
        Iterable<News> list = newsRepository.findAllByCategory(category);
        if (category != null) {
            return list;
        } else {
            return null;
        }
    }

    public NewsDTO searchByHeader(NewsDTO newsDTO) {
        News news = newsRepository.findAllByHeader(newsDTO.getHeader());
        newsDTO = newsMapper.createNewsDTO(news);
        if (news != null) {
            return newsDTO;
        } else {
            return null;
        }
    }

    public NewsDTO searchByBody(NewsDTO newsDTO) {
        News news = newsRepository.findAllByBody(newsDTO.getBody());
        newsDTO = newsMapper.createNewsDTO(news);
        if (news != null) {
            return newsDTO;
        } else {
            return null;
        }
    }

    public Iterable<News> searchByAll() {
        return newsRepository.findAll();
    }
}
