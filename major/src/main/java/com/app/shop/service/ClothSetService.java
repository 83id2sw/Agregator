package com.app.shop.service;

import com.app.shop.model.Category;
import com.app.shop.model.ClothSet;
import com.app.shop.repository.ClothSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClothSetService {

    @Autowired
    ClothSetRepository clothSetRepository;

    public void addClothSet(ClothSet clothSet) {
        clothSetRepository.save(clothSet);
    }
}
