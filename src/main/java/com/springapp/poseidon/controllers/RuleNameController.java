package com.springapp.poseidon.controllers;

import com.springapp.poseidon.domain.RuleName;
import com.springapp.poseidon.service.RuleNameService;
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
public class RuleNameController {

    @Autowired
    private RuleNameService ruleNameService;

    @RequestMapping("/ruleName/list")
    public String home(Model model) {
        model.addAttribute("rulesNameList", ruleNameService.getRulesName());

        // TODO: find all RuleName, add to model
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            ruleNameService.addRuleName(ruleName);
            model.addAttribute("rulesNameList", ruleNameService.getRulesName());
            return "redirect:/ruleName/list";
        }

        // TODO: check data valid and save to db, after saving return RuleName list
        return "ruleName/add";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) throws Exception {
        RuleName ruleName = ruleNameService.getRuleNameById(id);
        model.addAttribute("ruleName", ruleName);

        // TODO: get RuleName by Id and to model then show to the form
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "ruleName/update";
        }
        ruleName.setId(id);
        ruleNameService.addRuleName(ruleName);
        model.addAttribute("rulesNameList", ruleNameService.getRulesName());

        // TODO: check required fields, if valid call service to update RuleName and return RuleName list
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) throws Exception {
        ruleNameService.deleteRuleNameById(id);
        model.addAttribute("rulesNameList", ruleNameService.getRulesName());

        // TODO: Find RuleName by Id and delete the RuleName, return to Rule list
        return "redirect:/ruleName/list";
    }
}
