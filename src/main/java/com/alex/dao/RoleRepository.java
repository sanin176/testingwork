package com.alex.dao;

import com.alex.model.User.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByRoleName(String roleName);
}
