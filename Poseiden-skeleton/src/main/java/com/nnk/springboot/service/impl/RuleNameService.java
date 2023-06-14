package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RuleNameService {

    private static final Logger logger = LoggerFactory.getLogger(RuleNameService.class);

    @Autowired
    private RuleNameRepository ruleNameRepository;

   public List<RuleName> findAll() {
        logger.debug("findAll() sollicité de RuleNameService");
        try {
            logger.info("findAll() effectuée de RuleNameService");
            return ruleNameRepository.findAll();

        }catch (Exception e){
            logger.error("Erreur au findAll() de RuleNameService", e);
            return null;
        }
    }

    public RuleName save(RuleName ruleName) {
        logger.debug("save() sollicité de RuleNameService");
        try {
            logger.info("save() effectuée de RuleNameService");
            return ruleNameRepository.save(ruleName);
        }catch (Exception e){
            logger.error("Erreur au save() de RuleNameService", e);
            return null;
        }
    }

    public Optional<RuleName> findById(Integer id) {
        logger.debug("findById() sollicité de RuleNameService");
        try {
            logger.info("findById() effectuée de RuleNameService");
            return ruleNameRepository.findById(id);

        }catch (Exception e){
            logger.error("Erreur au findById() de RuleNameService", e);
            return null;
        }
    }

    public void delete(RuleName ruleName) {
        logger.debug("delete() sollicité de RuleNameService");
        try {
            logger.info("delete() effectuée de RuleNameService");
            ruleNameRepository.delete(ruleName);

        }catch (Exception e){
            logger.error("Erreur au delete() de RuleNameService", e);
        }
    }
}
