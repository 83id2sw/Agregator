package com.app.shop.model;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Data
@Getter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private int id;

    private String name;
}
