package com.example.todo.repository;

import com.example.todo.entity.Role;
import com.example.todo.entity.enums.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role , Long> {

    boolean existsByRoleName(AppRole roleName);

    Role findByRoleName(AppRole roleName);
}
