package com.app.shop.repository;

import com.app.shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for user.
 **/
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Function for searching user by email.
     *
     @param email Name of product.
     *
     @return User.
     **/
    public Optional<User> findUserByEmail(String email);


}
