package com.example.demo.controller;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.NewsDTO;
import com.example.demo.persistence.entity.News;
import com.example.demo.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/news")
public class NewsController {

    private NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
    public NewsDTO createNews(@RequestBody NewsDTO newsDTO) {
        return newsService.save(newsDTO);
    }

    @GetMapping (path = "/delete/{id}")
    public String deleteNews(@PathVariable Long id) {
        return newsService.delete(id);
    }

    @PostMapping(path = "/update", consumes = "application/json", produces = "application/json")
    public ResponseEntity editNews(@RequestBody NewsDTO newsDTO) {
        NewsDTO result = newsService.update(newsDTO);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @PostMapping(path = "/search/all")
    public Iterable<News> searchAll() {
        return newsService.searchByAll();
    }

    @PostMapping(path = "/search/category", consumes = "application/json", produces = "application/json")
    public Iterable<News> searchByCategory(@RequestBody CategoryDTO categoryDTO) {
        return newsService.searchByCategory(categoryDTO);
    }

    @PostMapping(path = "/search/header", consumes = "application/json", produces = "application/json")
    public NewsDTO searchByHeader(@RequestBody NewsDTO newsDTO) {
        return newsService.searchByHeader(newsDTO);
    }

    @PostMapping(path = "/search/body", consumes = "application/json", produces = "application/json")
    public NewsDTO searchByBody(@RequestBody NewsDTO newsDTO) {
        return newsService.searchByBody(newsDTO);
    }
}
