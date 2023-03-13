package com.app.shop.controller;

import com.app.shop.dto.ProductDTO;
import com.app.shop.global.GlobalData;
import com.app.shop.model.Cart;
import com.app.shop.model.Product;
import com.app.shop.model.User;
import com.app.shop.repository.CartRepository;
import com.app.shop.repository.UserRepository;
import com.app.shop.service.CategoryService;
import com.app.shop.service.ProductService;
import com.app.shop.util.ClothesAPI;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/addToCart/{code}")
    public String addToCart(@AuthenticationPrincipal User user,  @PathVariable String code) throws IOException {
        JsonObject jsonObject = ClothesAPI.getObjectByCode(code);
        JsonObject jsonProduct = jsonObject.get("product").getAsJsonObject();
        Product product = new Product();
        product.setCategoryName(jsonProduct.get("categoryName").getAsString());
        product.setCode(code);
        product.setName(jsonProduct.get("name").getAsString());
        product.setCategory(categoryService.findCategoryByName(jsonObject.get("productType").getAsString()));
        productService.addProduct(product);

        Cart cart = cartRepository.findCartByUser(userRepository.findUserByEmail(user.getEmail()).get());

        List<Product> productsFromCart = cart.getProducts();
        productsFromCart.add(product);
        cart.setProducts(productsFromCart);

        cartRepository.save(cart);

        return "redirect:/";
    }

    @GetMapping("/cart")
    public String cartGet(Model model) {
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("cart", GlobalData.cart);
        return "cart";
    }



}
