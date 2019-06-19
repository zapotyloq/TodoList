package com.zapotylok.todolist.dao;

import com.zapotylok.todolist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
