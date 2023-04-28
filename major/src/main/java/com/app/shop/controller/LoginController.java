package com.app.shop.controller;

import com.app.shop.model.Cart;
import com.app.shop.model.Role;
import com.app.shop.model.User;
import com.app.shop.repository.CartRepository;
import com.app.shop.repository.RoleRepository;
import com.app.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for login page.
 * */
@Controller
public class LoginController {

    /**
     * Repository for cart.
     **/
    @Autowired
    CartRepository cartRepository;

    /**
     *  Used for storing a password that needs to be compared to a user-provided password at the time of authentication.
     **/
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Repository for user.
     **/
    @Autowired
    UserRepository userRepository;

    /**
     * Repository for role.
     **/
    @Autowired
    RoleRepository roleRepository;

    /**
     * Mapping for login page.
     *
     @return Page for log in.
     **/
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Mapping for register page.
     *
     @return Page for registration user's account.
     **/
    @GetMapping("/register")
    public String registerGet() {
        return "register";
    }

    /**
     * Mapping for /register page.
     *
     @param user Authorized user.
     *
     @param request User's request.
     *
     @return Page for registered users.
     **/
    @PostMapping("/register")
    public String registerPost(@ModelAttribute("user")User user, HttpServletRequest request) throws ServletException {
        String password = user.getPassword();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findById(2).get());
        user.setRoles(roles);
        userRepository.save(user);

        Cart cart = new Cart();
        cart.setUser(user);
        cartRepository.save(cart);
        request.login(user.getEmail(), password);
        return "redirect:/";
    }
}
