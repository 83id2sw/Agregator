package com.app.shop.repository;

import com.app.shop.model.Category;
import com.app.shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repository for product.
 **/
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Function for searching product by id.
     *
     @param id Product's ident.
     *
     @return Product.
     **/
    Product findProductById(Long id);

    /**
     * Function for searching all products by id.
     *
     @param id Product's ident.
     *
     @return List of products.
     **/
    List<Product> findAllByCategoryId(int id);

    /**
     * Function for searching all products by sex and category.
     *
     @param sex Sex of product.
     *
     @param category Category of product.
     *
     @return List of products.
     **/
    List<Product> findAllBySexAndCategory(String sex, Category category);

    /**
     * Function for searching all products by sex.
     *
     @param sex Sex of product.
     *
     @return List of products.
     **/
    List<Product> findAllBySex(String sex);

    /**
     * Function for searching product by code.
     *
     @param code Code of product.
     *
     @return Product.
     **/
    Product findProductByCode(String code);

    /**
     * Function for searching all products by name and sex.
     *
     @param keyword Name of product.
     *
     @param sex Sex of product.
     *
     @return List of products.
     **/
    @Query("select p from Product p where p.name like %?1% and p.sex=?2")
    List<Product> searchAllByNameAndSex(String keyword, String sex);

    /**
     * Function for searching all products by name, sex and category.
     *
     @param keyword Name of product.
     *
     @param sex Sex of product.
     *
     @param category Category of product.
     *
     @return List of products.
     **/
    @Query("select p from Product p where p.name like %?1% and p.sex=?2 and p.category=?3")
    List<Product> searchAllByNameAndSexCAndCategory(String keyword, String sex, Category category);

}
