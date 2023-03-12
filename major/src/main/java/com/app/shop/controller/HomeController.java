package com.app.shop.controller;

import com.app.shop.service.CategoryService;
import com.app.shop.service.ProductService;
import com.app.shop.util.ClothesAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        return "index";
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
        model.addAttribute("product", ClothesAPI.getObjectByCode(code));
        return "viewProduct";
    }

//    @GetMapping("/shop/category/{id}")
//    public String shopByCategory(Model model, @PathVariable int id) {
//        model.addAttribute("categories", categoryService.getAllCategory());
//        model.addAttribute("products", productService.getAllProductsByCategoryId(id));
//
//        return "shop";
//    }

//    @GetMapping("/shop/viewproduct/{id}")
//    public String viewProduct(Model model, @PathVariable long id) {
//        model.addAttribute("product", productService.getProductById(id).get());
//
//        return "viewProduct";
//    }
}
