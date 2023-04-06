package com.app.shop.service;

import com.app.shop.model.Category;
import com.app.shop.model.Product;
import com.app.shop.repository.CartRepository;
import com.app.shop.repository.CategoryRepository;
import com.app.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }
    public void addProduct(Product product) {
        productRepository.save(product);
    }


    public List<Product> findAllBySexAndCategory(String sex, Category category){
        return productRepository.findAllBySexAndCategory(sex, category);
    }
    public List<Product> findAllBySex(String sex){
        return productRepository.findAllBySex(sex);
    }

    public Product findProductByCode(String code){
        return productRepository.findProductByCode(code);
    }

    public List<Product> searchAllByName(String keyword, String sex, Integer category) {
        if (category == -1){
            return productRepository.searchAllByNameAndSex(keyword, sex);
        }
        return productRepository.searchAllByNameAndSexCAndCategory(keyword, sex, categoryRepository.findCategoryById(category));
    }

}
