package com.app.shop.repository;

import com.app.shop.model.Category;
import com.app.shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategoryId(int id);

    List<Product> findAllBySexAndCategory(String sex, Category category);

    List<Product> findAllBySex(String sex);

    Product findProductByCode(String code);

    @Query("select p from Product p where p.name like %?1% and p.sex=?2")
    List<Product> searchAllByNameAndSex(String keyword, String sex);

    @Query("select p from Product p where p.name like %?1% and p.sex=?2 and p.category=?3")
    List<Product> searchAllByNameAndSexCAndCategory(String keyword, String sex, Category category);

}
