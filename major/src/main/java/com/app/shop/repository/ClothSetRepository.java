package com.app.shop.repository;

import com.app.shop.model.ClothSet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClothSetRepository extends JpaRepository<ClothSet, Long> {
    ClothSet findClothSetById(Integer id);
}
