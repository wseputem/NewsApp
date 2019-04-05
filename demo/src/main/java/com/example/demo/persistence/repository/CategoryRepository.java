package com.example.demo.persistence.repository;

import com.example.demo.persistence.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
