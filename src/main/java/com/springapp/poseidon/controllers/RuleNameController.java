package com.springapp.poseidon.controllers;

import com.springapp.poseidon.domain.RuleName;
import com.springapp.poseidon.service.RuleNameService;
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
public class RuleNameController {

    private final RuleNameService ruleNameService;

    public RuleNameController(RuleNameService ruleNameService) {
        this.ruleNameService = ruleNameService;
    }

    @RequestMapping("/ruleName/list")
    public String home(Model model) {
        log.info("Get all the rule names");
        model.addAttribute("rulesNameList", ruleNameService.getRulesName());
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        log.info("Get the add rule name page");
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        log.info("Validate the rule name added");
        if (!result.hasErrors()) {
            ruleNameService.addRuleName(ruleName);
            model.addAttribute("rulesNameList", ruleNameService.getRulesName());
            return "redirect:/ruleName/list";
        }
        return "ruleName/add";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) throws Exception {
        log.info("Get the update rule name page");
        RuleName ruleName = ruleNameService.getRuleNameById(id);
        model.addAttribute("ruleName", ruleName);
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
        log.info("The rule name has been updated");
        if (result.hasErrors()) {
            return "ruleName/update";
        }
        ruleName.setId(id);
        ruleNameService.addRuleName(ruleName);
        model.addAttribute("rulesNameList", ruleNameService.getRulesName());
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) throws Exception {
        log.info("The rule name has been deleted");
        ruleNameService.deleteRuleNameById(id);
        model.addAttribute("rulesNameList", ruleNameService.getRulesName());
        return "redirect:/ruleName/list";
    }
}
