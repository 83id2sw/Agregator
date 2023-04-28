package com.app.shop.service;

import com.app.shop.model.Category;
import com.app.shop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for category.
 **/
@Service
public class CategoryService {

    /**
     * Repository for category.
     **/
    @Autowired
    CategoryRepository categoryRepository;

    /**
     * Function for getting all categories.
     *
     @return List of category.
     **/
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    /**
     * Function for adding category by category.
     *
     @param category Category of product.
     **/
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    /**
     * Function for removing category by id.
     *
     @param id Ident of product.
     **/
    public void removeCategoryById(int id) {
        categoryRepository.deleteById(id);
    }

    /**
     * Function for searching category by name.
     *
     @return category.
     **/
    public Category findCategoryByName(String name) {
        return categoryRepository.findCategoryByName(name);
    }

    /**
     * Function for searching category by id.
     *
     @param id Ident of category.
     *
     @return category.
     **/
    public Category getCategoryById(int id) {
        return categoryRepository.findCategoryById(id);
    }
}
