package com.app.shop.controller;

import com.app.shop.global.GlobalData;
import com.app.shop.model.User;
import com.app.shop.repository.CartRepository;
import com.app.shop.repository.UserRepository;
import com.app.shop.service.CategoryService;
import com.app.shop.service.ProductService;
import com.app.shop.util.ClothesAPI;
//import jdk.internal.icu.text.NormalizerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @Autowired
    CartRepository cartRepository;

    @GetMapping({"/", "/home"})
    public String home(@AuthenticationPrincipal User user, Model model) {

        user = user == null ?null: userRepository.findUserByEmail(user.getEmail()).get();


        model.addAttribute("cartCount", user != null && cartRepository.findCartByUser(user) != null && cartRepository.findCartByUser(user).getProducts().size() != 0
                ?cartRepository.findCartByUser(user).getProducts().size() : "");
        return "index";
    }

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal User user, Model model) {
        user = userRepository.findUserByEmail(user.getEmail()).get();

        model.addAttribute("profile", user);
        model.addAttribute("cartCount", cartRepository.findCartByUser(user) != null && cartRepository.findCartByUser(user).getProducts().size() != 0
                ?cartRepository.findCartByUser(user).getProducts().size() : "");
        return "profile";
    }


    @GetMapping("/shop/{sex}/{productType}")
    public String shop(Model model,@PathVariable String sex, @PathVariable(required = false) Integer productType) {
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("products", productService.getAllProduct());
        model.addAttribute("sex", sex);
        if (productType == -1) {
            model.addAttribute("newProducts", ClothesAPI.getAllArrays(sex));
        } else {
            model.addAttribute("newProducts", ClothesAPI.allItems.get(sex).getAsJsonObject().get(
                    categoryService.getCategoryById(productType).get().getName()
            ));
        }

        return "shop";
    }

    @GetMapping("/shop/viewproduct/{code}")
    public String shopByCategory(Model model, @PathVariable String code) {
        model.addAttribute("product", ClothesAPI.getObjectByCode(code).get("product").getAsJsonObject());
        return "viewProduct";
    }


}
