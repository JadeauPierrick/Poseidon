package com.springapp.poseidon.controllers;

import com.springapp.poseidon.domain.BidList;
import com.springapp.poseidon.service.BidListService;
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
public class BidListController {

    private final BidListService bidListService;

    private final GetUserInfoService getUserInfoService;

    public BidListController(BidListService bidListService, GetUserInfoService getUserInfoService) {
        this.bidListService = bidListService;
        this.getUserInfoService = getUserInfoService;
    }

    @RequestMapping("/bidList/list")
    public String home(Model model, Principal user) {
        log.info("Get all the bids");
        model.addAttribute("bidLists", bidListService.getBidLists());
        model.addAttribute("userInfo", getUserInfoService.getUserInfo(user));
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        log.info("Get the add bid page");
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        log.info("Validate the bid added");
        if(!result.hasErrors()) {
            Timestamp date = Timestamp.from(Instant.now());
            bid.setCreationDate(date);
            bidListService.addBid(bid);
            model.addAttribute("bidLists", bidListService.getBidLists());
            return "redirect:/bidList/list";
        }
        return "bidList/add";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) throws Exception {
        log.info("Get the update bid page");
        BidList bid = bidListService.getBidById(id);
        model.addAttribute("bidList", bid);
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
        log.info("The bid has been updated");
        if(result.hasErrors()) {
            return "bidList/update";
        }
        bidList.setBidListId(id);
        bidListService.addBid(bidList);
        model.addAttribute("bidLists", bidListService.getBidLists());
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) throws Exception {
        log.info("The bid has been deleted");
        bidListService.deleteBidById(id);
        model.addAttribute("bidLists", bidListService.getBidLists());
        return "redirect:/bidList/list";
    }
}
