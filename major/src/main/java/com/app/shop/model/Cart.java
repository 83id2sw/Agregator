package com.app.shop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Model fo Cart.
 * */
@Entity
@Data
@Table(name = "cart")
public class Cart {

    /**
     * Ident field.
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * User table field.
     * */
    @OneToOne
    private User user;

    /**
     * Products field: one cart - many products.
     * */
    @OneToMany(cascade = CascadeType.MERGE)
    private List<Product> products = new ArrayList<>();
}
