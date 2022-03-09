package com.springapp.poseidon.controllers;

import com.springapp.poseidon.domain.CurvePoint;
import com.springapp.poseidon.service.CurvePointService;
import com.springapp.poseidon.service.GetUserInfoService;
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
import java.sql.Timestamp;
import java.time.Instant;

@Slf4j
@Controller
public class CurveController {

    private final CurvePointService curvePointService;

    private final GetUserInfoService getUserInfoService;

    public CurveController(CurvePointService curvePointService, GetUserInfoService getUserInfoService) {
        this.curvePointService = curvePointService;
        this.getUserInfoService = getUserInfoService;
    }

    @RequestMapping("/curvePoint/list")
    public String home(Model model, Principal user) {
        log.info("Get all the curve points");
        model.addAttribute("curvesList", curvePointService.getCurvePoints());
        model.addAttribute("userInfo", getUserInfoService.getUserInfo(user));
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint bid) {
        log.info("Get the add curve point page");
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        log.info("Validate the curve point added");
        if (!result.hasErrors()) {
            Timestamp date = Timestamp.from(Instant.now());
            curvePoint.setCreationDate(date);
            curvePointService.addCurvePoint(curvePoint);
            model.addAttribute("curvesList", curvePointService.getCurvePoints());
            return "redirect:/curvePoint/list";
        }
        return "curvePoint/add";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) throws Exception {
        log.info("Get the update curve point page");
        CurvePoint curvePoint = curvePointService.getCurvePointById(id);
        model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
        log.info("The curve point has been updated");
        if (result.hasErrors()) {
            return "curvePoint/update";
        }
        curvePoint.setId(id);
        curvePointService.addCurvePoint(curvePoint);
        model.addAttribute("curvesList", curvePointService.getCurvePoints());
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) throws Exception {
        log.info("The curve point has been deleted");
        curvePointService.deleteCurvePointById(id);
        model.addAttribute("curvesList", curvePointService.getCurvePoints());
        return "redirect:/curvePoint/list";
    }
}
