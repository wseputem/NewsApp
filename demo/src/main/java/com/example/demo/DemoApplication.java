package com.example.demo;

import com.example.demo.persistence.entity.Category;
import com.example.demo.persistence.entity.News;
import com.example.demo.persistence.repository.CategoryRepository;
import com.example.demo.persistence.repository.NewsRepository;
import com.example.demo.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
