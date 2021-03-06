package com.springapp.poseidon.controllers;

import com.springapp.poseidon.service.GetUserInfoService;
import com.springapp.poseidon.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Slf4j
@Controller
@RequestMapping("app")
public class LoginController {

    private final UserService userService;

    private final GetUserInfoService getUserInfoService;

    public LoginController(UserService userService, GetUserInfoService getUserInfoService) {
        this.userService = userService;
        this.getUserInfoService = getUserInfoService;
    }

    @GetMapping("login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

    @GetMapping("secure/article-details")
    public ModelAndView getAllUserArticles() {
        log.info("Get user articles");
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", userService.getUsers());
        mav.setViewName("user/list");
        return mav;
    }

    @GetMapping("error")
    public ModelAndView error(Principal user) {
        log.info("Error 403, the request is not authorized");
        ModelAndView mav = new ModelAndView();
        String errorMessage= "You are not authorized for the requested data.";
        mav.addObject("errorMsg", errorMessage);
        mav.addObject("userInfo", getUserInfoService.getUserInfo(user));
        mav.setViewName("403");
        return mav;
    }
}
