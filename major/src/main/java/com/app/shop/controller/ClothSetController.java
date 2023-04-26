package com.app.shop.controller;


import com.app.shop.model.*;
import com.app.shop.repository.CartRepository;
import com.app.shop.repository.ClothSetRepository;
import com.app.shop.repository.UserRepository;
import com.app.shop.service.CategoryService;
import com.app.shop.service.ClothSetService;
import com.app.shop.service.ProductService;
import com.google.gson.JsonElement;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class ClothSetController {

    @Autowired
    ClothSetRepository clothSetRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ClothSetService clothSetService;

    @Autowired
    CartRepository cartRepository;

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

    @PostMapping("/lookamaker/add")
    public String lookamekerPost(@AuthenticationPrincipal User user, @RequestBody Map<String, String> data){
        clothSetService.addClothSet(user, data);
        return "redirect:/";
    }

}
