package com.app.shop.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table
@Entity
@Getter
@Setter
public class ClothSet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @ManyToOne
    private User user;

    @OneToMany(cascade = CascadeType.MERGE)
    private List<Product> products = new ArrayList<>();
}
