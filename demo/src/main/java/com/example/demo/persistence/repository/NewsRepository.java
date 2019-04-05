package com.example.demo.persistence.repository;

import com.example.demo.persistence.entity.Category;
import com.example.demo.persistence.entity.News;
import org.springframework.data.repository.CrudRepository;

public interface NewsRepository extends CrudRepository<News, Long> {
    Iterable<News> findAllByCategory(Category category);
    News findAllByHeader(String header);
    News findAllByBody(String body);
}
