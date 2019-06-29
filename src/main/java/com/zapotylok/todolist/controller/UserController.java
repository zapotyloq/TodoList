package com.zapotylok.todolist.controller;

import com.zapotylok.todolist.model.Task;
import com.zapotylok.todolist.model.User;
import com.zapotylok.todolist.service.SecurityService;
import com.zapotylok.todolist.service.TaskService;
import com.zapotylok.todolist.service.UserService;
import com.zapotylok.todolist.validator.TaskValidator;
import com.zapotylok.todolist.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Controller for {@link com.zapotylok.todolist.model.User}'s pages.
 */

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model, String error, String logout) {
        //form for registration
        model.addAttribute("userForm", new User());

        //
        User user = userService.findByUsername(securityService.findLoggedInUsername());
        if(user != null) {
            List<Task> taskList = taskService.findByUserId(user.getId());
            model.addAttribute("TASKS", taskList);
            model.addAttribute("taskForm", new Task());
        }

        return "index";
    }

    /*@RequestMapping(value = "/#signup", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "index";
    }*/

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "index";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

        return "redirect:/index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            SecurityContextHolder.clearContext();
            model.addAttribute("message", "Logged out successfully.");
        }

        return "redirect:/index";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model) {
        return "admin";
    }
}
