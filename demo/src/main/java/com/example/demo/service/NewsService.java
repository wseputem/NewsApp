package com.example.demo.service;

import com.example.demo.dto.NewsDTO;
import com.example.demo.mapper.NewsMapper;
import com.example.demo.persistence.entity.News;
import com.example.demo.persistence.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NewsService {
    private NewsRepository newsRepository;
    private NewsMapper newsMapper;

    @Autowired
    public NewsService(NewsRepository newsRepository, NewsMapper newsMapper) {
        this.newsRepository = newsRepository;
        this.newsMapper = newsMapper;
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

    public List<NewsDTO> searchByCategory(Long categoryId) {
        List<News> list = newsRepository.findAllByCategoryId(categoryId);
        return newsMapper.createNewsDTOList(list);
    }

    public List<NewsDTO> searchByHeader(String header) {
        List<News> newsList = newsRepository.findAllByHeader(header);
        if (newsList != null) {
            return newsMapper.createNewsDTOList(newsList);
        } else {
            return null;
        }
    }

    public List<NewsDTO> searchByBody(String body) {
        List<News> list = newsRepository.findAllByBody(body);
        return newsMapper.createNewsDTOList(list);
    }

    public List<NewsDTO> searchByAll() {
        List<News> list = newsRepository.findAll();
        return newsMapper.createNewsDTOList(list);
    }
}
