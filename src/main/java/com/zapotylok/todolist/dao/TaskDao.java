package com.zapotylok.todolist.dao;

import com.zapotylok.todolist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskDao extends JpaRepository<Task, Long> {
   Task findById(long id);
}
