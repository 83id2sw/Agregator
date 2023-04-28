package com.app.shop.service;

import com.app.shop.model.CustomUserDetail;
import com.app.shop.model.User;
import com.app.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service for Custom User Detail.
 **/
@Service
public class CustomUserDetailService implements UserDetailsService {

    /**
     * Repository for user.
     **/
    @Autowired
    UserRepository userRepository;

    /**
     * Function for loading user by username.
     *
     @param email Email of user.
     *
     @return user.map(CustomUserDetail::new).get().
     **/
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByEmail(email);
        user.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user.map(CustomUserDetail::new).get();
    }
}
