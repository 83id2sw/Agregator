package com.app.shop.repository;

import com.app.shop.model.Cart;
import com.app.shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for cart
 **/
public interface CartRepository extends JpaRepository<Cart, Integer> {

    /**
     * Function for searching cart by user.
     *
     @param user User.
     *
     @return User's cart.
     **/
    Cart findCartByUser(User user);

}
