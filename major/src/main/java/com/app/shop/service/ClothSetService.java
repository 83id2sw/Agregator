package com.app.shop.service;

import com.app.shop.model.Category;
import com.app.shop.model.ClothSet;
import com.app.shop.model.Product;
import com.app.shop.model.User;
import com.app.shop.repository.ClothSetRepository;
import com.app.shop.repository.ProductRepository;
import com.app.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ClothSetService {

    @Autowired
    ClothSetRepository clothSetRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    public void addClothSet(User user, Map<String, String> data) {
        user = userRepository.findUserByEmail(user.getEmail()).get();

        ClothSet clothSet = new ClothSet();
        clothSet.setName(data.get("name"));
        clothSet.setDescription(data.get("description"));
        clothSet.setUser(user);

        List<Product> products = new ArrayList<>();

        for (Map.Entry<String, String> clothe:data.entrySet()){
            if (clothe.getValue().equals("true")){
                products.add(productRepository.findProductById(Long.parseLong(clothe.getKey())));
            }
        }

        clothSet.setProducts(products);

        clothSetRepository.save(clothSet);
    }
}
