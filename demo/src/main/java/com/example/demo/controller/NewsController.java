package com.example.demo.controller;

import com.example.demo.dto.NewsDTO;
import com.example.demo.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping (path = "/delete/{id}")
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

    @GetMapping(path = "/search/all")
    public List<NewsDTO> searchAll() {
        return newsService.searchByAll();
    }

    @GetMapping(path = "/search/categoryId:{categoryId}")
    public List<NewsDTO> searchByCategory(@PathVariable Long categoryId) {
        return newsService.searchByCategory(categoryId);
    }

    @GetMapping(path = "/search/header", produces = "application/json")
    public List<NewsDTO> searchByHeader(@RequestBody String header) {
        return newsService.searchByHeader(header);
    }

    @GetMapping(path = "/search/body", produces = "application/json")
    public List<NewsDTO> searchByBody(@RequestBody String body) {
        return newsService.searchByBody(body);
    }
}
