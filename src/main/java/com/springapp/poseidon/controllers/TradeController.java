package com.springapp.poseidon.controllers;

import com.springapp.poseidon.domain.Trade;
import com.springapp.poseidon.service.GetUserInfoService;
import com.springapp.poseidon.service.TradeService;
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
public class TradeController {

    private final TradeService tradeService;

    private final GetUserInfoService getUserInfoService;

    public TradeController(TradeService tradeService, GetUserInfoService getUserInfoService) {
        this.tradeService = tradeService;
        this.getUserInfoService = getUserInfoService;
    }

    @RequestMapping("/trade/list")
    public String home(Model model, Principal user) {
        log.info("Get all the trades");
        model.addAttribute("tradesList", tradeService.getTrades());
        model.addAttribute("userInfo", getUserInfoService.getUserInfo(user));
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Trade bid) {
        log.info("Get the add trade page");
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        log.info("Validate the trade added");
        if (!result.hasErrors()) {
            Timestamp date = Timestamp.from(Instant.now());
            trade.setCreationDate(date);
            tradeService.addTrade(trade);
            model.addAttribute("tradesList", tradeService.getTrades());
            return "redirect:/trade/list";
        }
        return "trade/add";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) throws Exception {
        log.info("Get the update trade page");
        Trade trade = tradeService.getTradeById(id);
        model.addAttribute("trade", trade);
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
        log.info("The trade has been updated");
        if (result.hasErrors()) {
            return "trade/update";
        }
        trade.setTradeId(id);
        tradeService.addTrade(trade);
        model.addAttribute("tradesList", tradeService.getTrades());
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) throws Exception {
        log.info("The trade has been deleted");
        tradeService.deleteTradeById(id);
        model.addAttribute("tradesList", tradeService.getTrades());
        return "redirect:/trade/list";
    }
}
