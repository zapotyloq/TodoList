package com.zapotylok.todolist.service;

import com.zapotylok.todolist.model.Task;

/**
 * Service class for {@link com.zapotylok.todolist.model.Task}
 */

public interface TaskService {

    void save(Task task);

    Task findById(long id);

    void edit(Task task);

    void delete(long id);
}