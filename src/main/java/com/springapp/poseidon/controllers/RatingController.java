package com.springapp.poseidon.controllers;

import com.springapp.poseidon.domain.Rating;
import com.springapp.poseidon.service.GetUserInfoService;
import com.springapp.poseidon.service.RatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@Slf4j
@Controller
public class RatingController {

    private final RatingService ratingService;

    private final GetUserInfoService getUserInfoService;

    public RatingController(RatingService ratingService, GetUserInfoService getUserInfoService) {
        this.ratingService = ratingService;
        this.getUserInfoService = getUserInfoService;
    }

    @RequestMapping("/rating/list")
    public String home(Model model, Principal user) {
        log.info("Get all the ratings");
        model.addAttribute("ratingsList", ratingService.getRatings());
        model.addAttribute("userInfo", getUserInfoService.getUserInfo(user));
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        log.info("Get the add rating page");
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        log.info("Validate the rating added");
        if (!result.hasErrors()) {
            ratingService.addRating(rating);
            model.addAttribute("ratingsList", ratingService.getRatings());
            return "redirect:/rating/list";
        }
        return "rating/add";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) throws Exception {
        log.info("Get the update rating page");
        Rating rating = ratingService.getRatingById(id);
        model.addAttribute("rating", rating);
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
        log.info("The rating has been updated");
        if (result.hasErrors()) {
            return "rating/update";
        }
        rating.setId(id);
        ratingService.addRating(rating);
        model.addAttribute("ratingsList", ratingService.getRatings());
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) throws Exception {
        log.info("The rating has been deleted");
        ratingService.deleteRatingById(id);
        model.addAttribute("ratingsList", ratingService.getRatings());
        return "redirect:/rating/list";
    }
}
