package com.zapotylok.todolist.controller;

import com.zapotylok.todolist.model.Task;
import com.zapotylok.todolist.model.User;
import com.zapotylok.todolist.service.TaskService;
import com.zapotylok.todolist.service.SecurityService;
import com.zapotylok.todolist.service.UserService;
import com.zapotylok.todolist.validator.TaskValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.WebDataBinder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Controller for {@link com.zapotylok.todolist.model.Task}'s pages.
 */

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskValidator taskValidator;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("taskForm", new Task());

        return "create";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute("taskForm") Task taskForm, BindingResult bindingResult, Model model) {
        taskValidator.validate(taskForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "create";
        }

        User user = userService.findByUsername(securityService.findLoggedInUsername());
        taskForm.setUser_id(user.getId());
        taskForm.setStatus(false);
        taskService.save(taskForm);

        return "redirect:/welcome";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        User user = userService.findByUsername(securityService.findLoggedInUsername());
        List<Task> taskList = taskService.findByUserId(user.getId());
        model.addAttribute("TASKS", taskList);
        return "welcome";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") int id) {
        taskService.delete(id);
        return "redirect:/welcome";
    }

    @RequestMapping(value = "/change_status/{id}", method = RequestMethod.GET)
    public String changeStatus(@PathVariable("id") int id) {
        Task task = taskService.findById(id);
        task.setStatus(!task.isStatus());
        taskService.save(task);
        return "redirect:/welcome";
    }
}
