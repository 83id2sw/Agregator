package com.app.shop.controller;


import com.app.shop.model.*;
import com.app.shop.repository.CartRepository;
import com.app.shop.repository.ClothSetRepository;
import com.app.shop.repository.UserRepository;
import com.app.shop.service.ClothSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
* Controller for cloth sets.
* */
@Controller
public class ClothSetController {

    /**
     * Repository for cloth sets
     * */
    @Autowired
    ClothSetRepository clothSetRepository;

    /**
     * Repository for user.
     * */
    @Autowired
    UserRepository userRepository;

    /**
     * Service for cloth sets.
     * */
    @Autowired
    ClothSetService clothSetService;

    /**
     * Repository for cart.
     * */
    @Autowired
    CartRepository cartRepository;

    /**
     * Mapping for getting lookamaker page.
     *
     @param user Authorized user.
     *
     @return Page for filling in fields.
     **/
    @GetMapping("/lookamaker")
    public String lookmaker(@AuthenticationPrincipal User user, Model model) {
        user = userRepository.findUserByEmail(user.getEmail()).get();

        model.addAttribute("cartCount", cartRepository.findCartByUser(user) != null && cartRepository.findCartByUser(user).getProducts().size() != 0
                ?cartRepository.findCartByUser(user).getProducts().size() : "");

        List<Product> cart = cartRepository.findCartByUser(userRepository.findUserByEmail(user.getEmail()).get()).getProducts();
        model.addAttribute("cart", cart);
        model.addAttribute("createdSet", new ClothSet());

        return "lookamaker";
    }

    /**
     * Mapping for lookamaker/add page.
     *
     @param user Authorized user.
     *
     @param data Data for cloth set.
     *
     @return page for creating cloth set.
      **/
    @PostMapping("/lookamaker/add")
    public String lookamekerPost(@AuthenticationPrincipal User user, @RequestBody Map<String, String> data){
        return clothSetService.addClothSet(user, data);
    }

}
