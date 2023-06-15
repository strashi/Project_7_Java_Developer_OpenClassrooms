package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;

import java.util.List;
import java.util.Optional;

public interface IRuleNameService {

    public List<RuleName> findAll() ;

    public RuleName save(RuleName ruleName);

    public Optional<RuleName> findById(Integer id) ;

    public void delete(RuleName ruleName);
}
