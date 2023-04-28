package com.app.shop.controller;


import com.app.shop.model.Cart;
import com.app.shop.model.Product;
import com.app.shop.model.User;
import com.app.shop.repository.CartRepository;
import com.app.shop.repository.UserRepository;
import com.app.shop.service.CategoryService;
import com.app.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Controller for cart.
 * */
@Controller
public class CartController {

    /**
     * Repository for cart.
     * */
    @Autowired
    CartRepository cartRepository;

    /**
     * Repository for user.
     * */
    @Autowired
    UserRepository userRepository;

    /**
     * Service for products.
     * */
    @Autowired
    ProductService productService;

    /**
     * Service for categories.
     * */
    @Autowired
    CategoryService categoryService;

    /**
     * Mapping for addToCart/{code} page.
     *
     @param user Authorized user.
     *
     @param code Code of product.
     *
     @return Page for adding products to cart.
     **/
    @GetMapping("/addToCart/{code}")
    public String addToCart(@AuthenticationPrincipal User user,  @PathVariable String code) throws IOException {
        Product product = productService.findProductByCode(code);

        Cart cart = cartRepository.findCartByUser(userRepository.findUserByEmail(user.getEmail()).get());

        List<Product> productsFromCart = cart.getProducts();
        productsFromCart.add(product);
        cart.setProducts(productsFromCart);
        cartRepository.save(cart);

        return "redirect:/";
    }

    /**
     * Mapping for cart page.
     *
     @param user Authorized user.
     *
     @return Page for products on the cart page.
     **/
    @GetMapping("/cart")
    public String cartGet(Model model, @AuthenticationPrincipal User user) {
        List<Product> cart = cartRepository.findCartByUser(userRepository.findUserByEmail(user.getEmail()).get()).getProducts();
        model.addAttribute("cart", cart);
        model.addAttribute("cartCount", cart.size());
        model.addAttribute("total", cart.stream().map(i -> Double.parseDouble(i.getPrice())).mapToDouble(Double::doubleValue).sum());

        return "cart";
    }

    /**
     * Mapping for cart/removeItem/{code} page.
     *
     @param user Authorized user.
     *
     @param code Code of product.
     *
     @return Page for removing products from user's cart.
     **/
    @GetMapping("/cart/removeItem/{code}")
    public String removeItem(@PathVariable String code, @AuthenticationPrincipal User user) {
        Cart cart = cartRepository.findCartByUser(userRepository.findUserByEmail(user.getEmail()).get());
        List<Product> productsFromCart = cart.getProducts();

        int index = 0;
        for (int i = 0; i < productsFromCart.size(); ++i){
            if (Objects.equals(productsFromCart.get(i).getCode(), code)) {
                index = i;
            }
        }

        productsFromCart.remove(index);
        cartRepository.save(cart);

        return "redirect:/cart";
    }

}
