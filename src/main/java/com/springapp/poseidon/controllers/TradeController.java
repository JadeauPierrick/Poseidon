package com.springapp.poseidon.controllers;

import com.springapp.poseidon.domain.Trade;
import com.springapp.poseidon.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @RequestMapping("/trade/list")
    public String home(Model model) {
        model.addAttribute("tradesList", tradeService.getTrades());
        // TODO: find all Trade, add to model
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Trade bid) {
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            tradeService.addTrade(trade);
            model.addAttribute("tradesList", tradeService.getTrades());
            return "redirect:/trade/list";
        }
        // TODO: check data valid and save to db, after saving return Trade list
        return "trade/add";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) throws Exception {
        Trade trade = tradeService.getTradeById(id);
        model.addAttribute("trade", trade);
        // TODO: get Trade by Id and to model then show to the form
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "trade/update";
        }
        trade.setTradeId(id);
        tradeService.addTrade(trade);
        model.addAttribute("tradesList", tradeService.getTrades());
        // TODO: check required fields, if valid call service to update Trade and return Trade list
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) throws Exception {
        tradeService.deleteTradeById(id);
        model.addAttribute("tradesList", tradeService.getTrades());
        // TODO: Find Trade by Id and delete the Trade, return to Trade list
        return "redirect:/trade/list";
    }
}
