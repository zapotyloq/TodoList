package com.zapotylok.todolist.service;

import com.zapotylok.todolist.dao.TaskDao;
import com.zapotylok.todolist.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link TaskService} interface.
 */

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDao taskDao;

    @Override
    public void save(Task task) {
        taskDao.save(task);
    }

    @Override
    public Task findById(long id) {
        return taskDao.findById(id);
    }

    @Override
    public void edit(Task task){
        Task t = taskDao.findById(task.getId());
        t.setName(task.getName());
        t.setDescription(task.getDescription());
        t.setStart(task.getStart());
        t.setDue(task.getStart());
        t.setStatus(task.getStatus());
    }

    @Override
    public void delete(long id){
        taskDao.delete(id);
    }
}