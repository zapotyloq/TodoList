package com.zapotylok.todolist.service;

import com.zapotylok.todolist.model.Task;

import java.util.List;

/**
 * Service class for {@link com.zapotylok.todolist.model.Task}
 */

public interface TaskService {

    void save(Task task);

    Task findById(long id);

    List<Task> findByUserId(long user_id);

    void delete(long id);
}