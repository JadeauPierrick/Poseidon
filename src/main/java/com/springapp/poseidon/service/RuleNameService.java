package com.springapp.poseidon.service;

import com.springapp.poseidon.domain.RuleName;

import java.util.List;

public interface RuleNameService {

    List<RuleName> getRulesName();

    RuleName getRuleNameById(Integer id) throws Exception;

    RuleName addRuleName(RuleName ruleName);

    void deleteRuleNameById(Integer id) throws Exception;
}
