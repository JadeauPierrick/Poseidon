package com.springapp.poseidon.service;

import com.springapp.poseidon.domain.RuleName;
import com.springapp.poseidon.repositories.RuleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RuleNameServiceImpl implements RuleNameService{

    @Autowired
    private RuleNameRepository ruleNameRepository;

    @Override
    public List<RuleName> getRulesName() {
        return ruleNameRepository.findAll();
    }

    @Override
    public RuleName getRuleNameById(Integer id) throws Exception {
        Optional<RuleName> ruleName = ruleNameRepository.findById(id);
        if (ruleName.isPresent()) {
            return ruleName.get();
        }else {
            throw new Exception("The rule name was not found");
        }
    }

    @Override
    public RuleName addRuleName(RuleName ruleName) {
        return ruleNameRepository.save(ruleName);
    }

    @Override
    public void deleteRuleNameById(Integer id) throws Exception {
        Optional<RuleName> ruleName = ruleNameRepository.findById(id);
        if (ruleName.isPresent()) {
            ruleNameRepository.deleteById(id);
        }else {
            throw new Exception("Invalid rule name Id : " + id);
        }
    }
}
