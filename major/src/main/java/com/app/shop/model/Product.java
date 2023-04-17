package com.app.shop.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    private String code;

    private String price;

    private String normalPicture;

    private String logoPicture;

    private String linkPdp;

    private String colors;

    private String brand;

    private String mainCategoryCode;

    private String sex;

    // Pants, Hat, etc..
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    //Men, Ladies
    private String categoryName;

}
