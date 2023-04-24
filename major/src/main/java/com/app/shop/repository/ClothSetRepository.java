package com.app.shop.repository;

import com.app.shop.model.ClothSet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClothSetRepository extends JpaRepository<ClothSet, Integer> {
    ClothSet findClothSetById(Integer id);
}
