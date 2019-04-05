package com.example.demo.persistence.repository;

import com.example.demo.persistence.entity.News;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NewsRepository extends CrudRepository<News, Long> {
    List<News> findAllByHeader(String header);
    List<News> findAllByBody(String body);
    List<News> findAllByCategoryId(Long id);
    @Override
    List<News> findAll();
}
