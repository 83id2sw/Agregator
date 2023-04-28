package com.app.shop.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * User model
 **/
@Entity
@Data
@Table(name = "users")
public class User {

    /**
     * Ident field.
     **/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * First name field.
     **/
    @NotEmpty
    @Column(nullable = false)
    private String firstName;

    /**
     * Last name field.
     **/
    private String lastName;

    /**
     * Email field.
     **/
    @Column(nullable = false, unique = true)
    @NotEmpty
    @Email(message = "{errors.invalid_email}")
    private String email;

    /**
     * Password field.
     **/
    private String password;

    /**
     * Roles field.
     **/
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
                inverseJoinColumns = {@JoinColumn (name = "ROLE_ID", referencedColumnName = "ID")}
    )
    private List<Role> roles;

    /**
     * User constructor.
     *
     @param user User.
     *
     **/
    public User(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.roles = user.getRoles();
    }

    /**
     * Default user constructor.
     *
     **/
    public User() {

    }

}
