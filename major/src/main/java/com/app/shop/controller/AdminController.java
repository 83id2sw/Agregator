package com.app.shop.controller;

import com.app.shop.model.Category;
import com.app.shop.service.CategoryService;
import com.app.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for admin.
 * */
@Controller
public class AdminController {

    /**
     * Service for categories.
     * */
    @Autowired
    CategoryService categoryService;

    /**
     * Service for products.
     * */
    @Autowired
    ProductService productService;

    /**
     * Mapping for getting admin page.
     *
     @return Page for admins.
     **/
    @GetMapping("/admin")
    public String adminHome() {
        return "adminHome";
    }

    /**
     * Mapping for getting categories page.
     *
     @return Page for all categories.
     **/
    @GetMapping("/admin/categories")
    public String getCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategory());
        return "categories";
    }

    /**
     * Mapping for getting categories/add page.
     *
     @return Page for filling in fields on the page.
     **/
    @GetMapping("/admin/categories/add")
    public String getCategoriesAdd(Model model) {
        model.addAttribute("category", new Category());
        return "categoriesAdd";
    }

    /**
     * Mapping for categories/add page.
     *
     @param category Category object.
     *
     @return Page for creating categories.
     **/
    @PostMapping("/admin/categories/add")
    public String postCategoriesAdd(@ModelAttribute("category") Category category) {
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }

    /**
     * Mapping for categories/delete/{id} page.
     *
     @param id Identification of category.
     *
     @return Page for removing categories.
     **/
    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCategory(@PathVariable int id) {
        categoryService.removeCategoryById(id);
        return "redirect:/admin/categories";
    }

    /**
     * Mapping for categories/update/{id} page.
     *
     @param id Identification of category.
     *
     @return Page for updating categories.
     **/
    @GetMapping("/admin/categories/update/{id}")
    public String updateCategory(@PathVariable int id, Model model) {
        Category category = categoryService.getCategoryById(id);
        if (category != null) {
            model.addAttribute("category", category);
            return "categoriesAdd";
        } else {
            return "404";
        }
    }

    //Product
    @GetMapping("/admin/products")
    public String products(Model model) {
        model.addAttribute("products", productService.getAllProduct());
        return "products";
    }

}
