package com.zapotylok.todolist.validator;

import com.zapotylok.todolist.model.Task;
import com.zapotylok.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validator for {@link com.zapotylok.todolist.model.Task} class,
 * implements {@link Validator} interface.
 */

@Component
public class TaskValidator implements Validator {

    @Autowired
    private TaskService taskService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Task.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Task task = (Task) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Required");
        if (task.getStart().after(task.getDue())) {
            errors.rejectValue("start", "Different.taskForm.date");
        }
    }
}
