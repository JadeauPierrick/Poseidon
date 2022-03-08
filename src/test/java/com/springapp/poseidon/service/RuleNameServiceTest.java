package com.springapp.poseidon.service;

import com.springapp.poseidon.domain.RuleName;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RuleNameServiceTest {

    @Autowired
    private RuleNameService ruleNameService;

    private RuleName rule;

    @BeforeAll
    private void setUp() {
        rule = new RuleName();
        rule.setName("Rule Name");
        rule.setDescription("Description");
        rule.setJson("Json");
        rule.setTemplate("Template");
        rule.setSqlStr("SQL");
        rule.setSqlPart("SQL Part");
    }

    @Test
    @Order(1)
    public void addRuleNameTest() {
        ruleNameService.addRuleName(rule);
        assertNotNull(rule.getId());
        assertThat(rule.getName()).isEqualTo("Rule Name");
    }

    @Test
    @Order(2)
    public void getRulesNameTest() {
        List<RuleName> rules = ruleNameService.getRulesName();
        assertTrue(rules.size() > 0);
    }

    @Test
    @Order(3)
    public void getRuleNameByIdTest() throws Exception {
        RuleName r = ruleNameService.getRuleNameById(rule.getId());
        assertThat(r.getId()).isEqualTo(rule.getId());
    }

    @Test
    @Order(4)
    public void getRuleNameByNullIdTest() {
        try {
            ruleNameService.getRuleNameById(10);
        } catch (Exception exception) {
            assertThat(exception.getMessage()).contains("The rule name was not found");
        }
    }

    @Test
    @Order(5)
    public void deleteRuleNameByIdTest() throws Exception {
        ruleNameService.deleteRuleNameById(rule.getId());
        List<RuleName> rules = ruleNameService.getRulesName();
        assertThat(rules.size()).isEqualTo(0);
    }

    @Test
    @Order(6)
    public void deleteRuleNameByNullIdTest() {
        try {
            ruleNameService.deleteRuleNameById(10);
        } catch (Exception exception) {
            assertThat(exception.getMessage()).contains("Invalid rule name Id : " + 10);
        }
    }
}
