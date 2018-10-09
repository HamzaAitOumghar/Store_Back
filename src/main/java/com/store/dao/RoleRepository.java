package com.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.security.Role;

public interface  RoleRepository extends JpaRepository<Role, Integer> {

}
