package com.app.shop.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Product model
 **/
@Entity
@Data
public class Product {

    /**
     * Ident field.
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Name field.
     * */
    private String name;

    /**
     * Code field.
     * */
    private String code;

    /**
     * Price field.
     * */
    private String price;

    /**
     * Normal picture field.
     * */
    private String normalPicture;

    /**
     * Logo picture field.
     * */
    private String logoPicture;

    /**
     * Product link field.
     * */
    private String linkPdp;

    /**
     * Colors field.
     * */
    private String colors;

    /**
     * Brand field.
     * */
    private String brand;

    /**
     * MainCategoryCode field.
     * */
    private String mainCategoryCode;

    /**
     * Sex field.
     * */
    private String sex;

    /**
     * Category field.
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    /**
     * Category name field.
     * */
    private String categoryName;

}
