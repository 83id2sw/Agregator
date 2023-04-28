package com.app.shop.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * CustomUserDetail model.
 * */
public class CustomUserDetail extends User implements UserDetails {

    /**
     * CustomUserDetail constructor.
     *
     @param user User.
     * */
    public CustomUserDetail(User user) {
        super(user);
    }

    /**
     * Function for getting authorities.
     *
     @return Authority list.
      * */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        super.getRoles().forEach(role -> {
            authorityList.add(new SimpleGrantedAuthority(role.getName()));
        });
        return authorityList;
    }

    /**
     * Function for getting user's name.
     *
     @return email.
      * */
    @Override
    public String getUsername() {
        return super.getEmail();
    }

    /**
     * Function for getting user's password.
     *
     @return password.
      * */
    @Override
    public String getPassword() {
        return super.getPassword();
    }

    /**
     * Function for getting info about expired account.
     *
     @return true.
      * */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Function for getting info about non-locked account.
     *
     @return true.
      * */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Function for getting info about non-expired Credentials.
     *
     @return true.
      * */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Function for getting info about enabled account.
     *
     @return true.
      * */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
