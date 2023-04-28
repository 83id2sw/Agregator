package com.app.shop.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * Role model
 **/
@Data
@Entity
@Table(name = "roles")
public class Role {

    /**
     * Ident field.
     **/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * Name field.
     **/
    @Column(nullable = false, unique = true)
    @NotEmpty
    private String name;

    /**
     * Users field.
     **/
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

}
