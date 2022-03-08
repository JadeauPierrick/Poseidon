package com.springapp.poseidon.controllers;

import com.springapp.poseidon.domain.User;
import com.springapp.poseidon.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/user/list")
    public String home(Model model) {
        log.info("Get all the users");
        model.addAttribute("users", userService.getUsers());
        return "user/list";
    }

    @GetMapping("/user/add")
    public String addUser(User bid) {
        log.info("Get the add user page");
        return "user/add";
    }

    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model) throws Exception {
        log.info("Validate the user added");
        if (!result.hasErrors()) {
            userService.addUser(user);
            model.addAttribute("users", userService.getUsers());
            return "redirect:/user/list";
        }
        return "user/add";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) throws Exception {
        log.info("Get the update user page");
        User user = userService.getUserById(id);
        user.setPassword("");
        model.addAttribute("user", user);
        return "user/update";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
                             BindingResult result, Model model) throws Exception {
        log.info("The user has been updated");
        if (result.hasErrors()) {
            return "user/update";
        }
        user.setId(id);
        userService.addUser(user);
        model.addAttribute("users", userService.getUsers());
        return "redirect:/user/list";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) throws Exception {
        log.info("The user has been deleted");
        userService.deleteUserById(id);
        model.addAttribute("users", userService.getUsers());
        return "redirect:/user/list";
    }
}
