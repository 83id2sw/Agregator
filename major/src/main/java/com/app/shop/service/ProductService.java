package com.app.shop.service;

import com.app.shop.model.Category;
import com.app.shop.model.Product;
import com.app.shop.repository.CategoryRepository;
import com.app.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for Product.
 **/
@Service
public class ProductService {

    /**
     * Repository for product.
     **/
    @Autowired
    ProductRepository productRepository;

    /**
     * Repository for category.
     **/
    @Autowired
    CategoryRepository categoryRepository;

    /**
     * Function for getting all products.
     *
     @return List of products.
     **/
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    /**
     * Function for adding products by product's entity.
     *
     @param product Product entity.
     **/
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    /**
     * Function for searching all products by sex and category.
     *
     @param sex Sex of product.
     *
     @param category Category of product.
     *
     @return List of products.
     **/
    public List<Product> findAllBySexAndCategory(String sex, Category category){
        return productRepository.findAllBySexAndCategory(sex, category);
    }

    /**
     * Function for searching all products by sex.
     *
     @param sex Sex of product.
     *
     @return List of products.
     **/
    public List<Product> findAllBySex(String sex){
        return productRepository.findAllBySex(sex);
    }

    /**
     * Function for searching product by code.
     *
     @param code Code of product.
     *
     @return Product.
     **/
    public Product findProductByCode(String code){
        return productRepository.findProductByCode(code);
    }

    /**
     * Function for searching all products by name, sex and category.
     *
     @param keyword Name of product.
     *
     @param sex Sex of product.
     *
     @param category Category of product.
     *
     @return List of product.
     **/
    public List<Product> searchAllByName(String keyword, String sex, Integer category) {
        if (category == -1){
            return productRepository.searchAllByNameAndSex(keyword, sex);
        }
        return productRepository.searchAllByNameAndSexCAndCategory(keyword, sex, categoryRepository.findCategoryById(category));
    }

}
