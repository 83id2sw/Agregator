package com.app.shop.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ClothSet model.
 * */
@Table
@Entity
@Getter
@Setter
public class ClothSet {

    /**
     * Ident field.
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * Name field.
     * */
    private String name;

    /**
     * Description field.
     * */
    private String description;

    /**
     * User table field.
     * */
    @ManyToOne
    private User user;

    /**
     * Products field: many products - many cloth sets.
     * */
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Product> products = new ArrayList<>();
}
