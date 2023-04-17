package com.app.shop.service;

import com.app.shop.model.Category;
import com.app.shop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    public void removeCategoryById(int id) {
        categoryRepository.deleteById(id);
    }

    public Category findCategoryByName(String name) {
        return categoryRepository.findCategoryByName(name);
    }

    public Category getCategoryById(int id) {
        return categoryRepository.findCategoryById(id);
    }
}
