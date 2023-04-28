package com.app.shop.configuration;

import com.app.shop.model.Role;
import com.app.shop.model.User;
import com.app.shop.repository.RoleRepository;
import com.app.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Configuration for GoogleOAuth2.
 * */
@Component
public class GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler {

    /**
     * Repository for role.
     **/
    @Autowired
    RoleRepository roleRepository;

    /**
     * Repository for user.
     **/
    @Autowired
    UserRepository userRepository;

    /**
     * Redirect strategy.
     **/
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     * Function for event, when Auth success: sending redirect to home page.
     *
     @param httpServletRequest Http Servlet Request.
     *
     @param httpServletResponse Http Servlet Response.
     *
     @param authentication Authentication.
     **/
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        String email = token.getPrincipal().getAttributes().get("email").toString();
        if (userRepository.findUserByEmail(email).isPresent()) {

        } else {
            User user = new User();
            user.setFirstName(token.getPrincipal().getAttributes().get("given_name").toString());
            user.setLastName(token.getPrincipal().getAttributes().get("family_name").toString());
            user.setEmail(email);
            List<Role> roles = new ArrayList<>();
            roles.add(roleRepository.findById(2).get());
            user.setRoles(roles);
            userRepository.save(user);

        }
        redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/");
    }


}
