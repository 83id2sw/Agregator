package com.app.shop.model;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

/**
 * Category model.
 * */
@Entity
@Data
@Getter
public class Category {

    /**
     * Ident field.
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private int id;

    /**
     * Name field.
     * */
    private String name;
}
