package com.app.shop.repository;

import com.app.shop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for category.
 **/
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    /**
     * Function for searching category by title.
     *
     @param name Category's title.
     *
     @return Category.
     **/
    public Category findCategoryByName(String name);

    /**
     * Function for searching category by id.
     *
     @param id Category's ident.
     *
     @return Category.
     **/
    public Category findCategoryById(Integer id);
}
