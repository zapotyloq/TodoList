package com.zapotylok.todolist.dao;

import com.zapotylok.todolist.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {

}
