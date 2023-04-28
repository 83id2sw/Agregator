package com.app.shop.repository;

import com.app.shop.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for Role.
 **/
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
