package com.app.shop.repository;

import com.app.shop.model.Cart;
import com.app.shop.model.Category;
import com.app.shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findCartByUser(User user);

}
