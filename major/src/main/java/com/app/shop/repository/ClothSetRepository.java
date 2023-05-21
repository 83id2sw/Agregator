package com.app.shop.repository;

import com.app.shop.model.ClothSet;
import com.app.shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for cloth set.
 **/
public interface ClothSetRepository extends JpaRepository<ClothSet, Integer> {

    /**
     * Function for searching cloth set by id.
     *
     @param id Set's ident.
     *
     @return Cloth set.
     **/
    ClothSet findClothSetById(Integer id);

    List<ClothSet> findClothSetsByUser(User user);

}
