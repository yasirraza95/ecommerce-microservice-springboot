package com.springboot.ecommerce.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.ecommerce.user.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}