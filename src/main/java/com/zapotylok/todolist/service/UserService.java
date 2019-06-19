package com.zapotylok.todolist.service;

import com.zapotylok.todolist.model.User;

/**
 * Service class for {@link com.zapotylok.todolist.model.User}
 */

public interface UserService {

    void save(User user);

    User findByUsername(String username);
}