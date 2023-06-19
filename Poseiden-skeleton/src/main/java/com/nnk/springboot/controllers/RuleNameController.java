package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.impl.RuleNameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
/**
 * Implementation of the controller for ruleName
 */
@Controller
public class RuleNameController {
    private static final Logger logger = LoggerFactory.getLogger(RuleNameController.class);

    @Autowired
    private RuleNameService ruleNameService;


    @RequestMapping("/ruleName/list")
    public String home(Model model) {
        logger.debug("home() sollicitée de RuleNameController");
        try {
            model.addAttribute("ruleNames", ruleNameService.findAll());
            logger.info("home() effectuée de RuleNameController");
            return "ruleName/list";
        }catch (Exception e){
            logger.error("Erreur au home() de RuleNameController", e);
            return null;
        }

    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        logger.debug("addRuleForm() sollicitée de RuleNameController");
        try {
            logger.info("addRuleForm() effectuée de RuleNameController");
            return "ruleName/add";
        }catch (Exception e){
            logger.error("Erreur au addRuleForm() de RuleNameController", e);
            return null;
        }
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        logger.debug("validate() sollicitée de RuleNameController");
        try {
            if (!result.hasErrors()) {
                ruleNameService.save(ruleName);
                logger.info("validate() effectuée de RuleNameController");
                return "redirect:/ruleName/list";
            }
            logger.info("validate() non effectuée de RuleNameController");
            return "ruleName/add";
        }catch (Exception e){
            logger.error("Erreur au validate() de RuleNameController", e);
            return null;
        }
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        logger.debug("showUpdateForm() sollicitée de RuleNameController");
        try {
            RuleName ruleName = ruleNameService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id " + id));
            model.addAttribute("ruleName", ruleName);
            logger.info("showUpdateForm() effectuée de RuleNameController");
            return "ruleName/update";
        }catch (Exception e){
            logger.error("Erreur au showUpdateForm() de RuleNameController", e);
            return null;
        }
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
        logger.debug("updateRuleName() sollicitée de RuleNameController");
        try {
            if (result.hasErrors()) {
                logger.info("updateRuleName() non effectuée de RuleNameController");
                return "ruleName/update";
            }
            ruleName.setId(id);
            ruleNameService.save(ruleName);
            logger.info("updateRuleName() effectuée de RuleNameController");
            return "redirect:/ruleName/list";
        }catch (Exception e){
            logger.error("Erreur au updateRuleName() de RuleNameController", e);
            return null;
        }
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        logger.debug("deleteRuleName() sollicitée de RuleNameController");
        try {
            RuleName ruleName = ruleNameService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id " + id));
            ruleNameService.delete(ruleName);
            logger.info("deleteRuleName() effectuée de RuleNameController");
            return "redirect:/ruleName/list";
        }catch (Exception e){
            logger.error("Erreur au deleteRuleName() de RuleNameController", e);
            return null;
        }
    }
}
