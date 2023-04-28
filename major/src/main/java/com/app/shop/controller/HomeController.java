package com.app.shop.controller;


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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Controller for home page.
 * */
@Controller
public class HomeController {

    /**
     * Repository for user.
     * */
    @Autowired
    UserRepository userRepository;

    /**
     * Service for category.
     * */
    @Autowired
    CategoryService categoryService;

    /**
     * Service for product.
     * */
    @Autowired
    ProductService productService;

    /**
     * Repository for cart.
     * */
    @Autowired
    CartRepository cartRepository;

    /**
     * Mapping for /home page.
     *
     @param user Authorized user.
     *
     @return Page for navigation between sections.
     **/
    @GetMapping({"/", "/home"})
    public String home(@AuthenticationPrincipal User user, Model model) {

        user = user == null ?null: userRepository.findUserByEmail(user.getEmail()).get();


        model.addAttribute("cartCount", user != null && cartRepository.findCartByUser(user) != null && cartRepository.findCartByUser(user).getProducts().size() != 0
                ?cartRepository.findCartByUser(user).getProducts().size() : "");
        return "index";
    }

    /**
     * Mapping for profile page.
     *
     @param user Authorized user.
     *
     @return Profile page of authorized user.
     **/
    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal User user, Model model) {
        user = userRepository.findUserByEmail(user.getEmail()).get();

        model.addAttribute("profile", user);
        model.addAttribute("cartCount", cartRepository.findCartByUser(user) != null && cartRepository.findCartByUser(user).getProducts().size() != 0
                ?cartRepository.findCartByUser(user).getProducts().size() : "");
        return "profile";
    }

    /**
     * Mapping for shop/{sex}/{productType} page.
     *
     @param sex Sex for shop page.
     *
     @param productType Type of product.
     *
     @return Page for navigation between products.
     **/
    @GetMapping("/shop/{sex}/{productType}")
    public String shop(Model model,@PathVariable String sex, @PathVariable(required = false) Integer productType) {
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("sex", sex);
        model.addAttribute("productType", productType);
        if (productType == -1) {
            model.addAttribute("newProducts", productService.findAllBySex(sex));
        } else {
            model.addAttribute("newProducts", productService.findAllBySexAndCategory(sex, categoryService.getCategoryById(productType)));
        }

        return "shop";
    }

    /**
     * Mapping for shop/viewproduct/{code} page.
     *
     @param code Sex for shop page.
     *
     @return Page for view product by code.
     **/
    @GetMapping("/shop/viewproduct/{code}")
    public String shopByCategory(Model model, @PathVariable String code) {
        model.addAttribute("product", productService.findProductByCode(code));
        return "viewProduct";
    }

    /**
     * Mapping for shopSearch/{sex}/{productType} page.
     *
     @param sex Sex for shop page.
     *
     @param productType Type of product.
     *
     @param stringSearch Search's string fo searching products on page.
     *
     @return Page for searching products.
     **/
    @GetMapping("/shopSearch/{sex}/{productType}")
    public String searchShop(Model model, @PathVariable String sex,
                             @PathVariable Integer productType, @ModelAttribute(name = "search") String stringSearch) {
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("sex", sex);
        model.addAttribute("productType", productType);
        model.addAttribute("newProducts", productService.searchAllByName(stringSearch, sex, productType));
        return "shop";
    }

}
