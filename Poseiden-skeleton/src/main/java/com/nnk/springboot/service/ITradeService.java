package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;

import java.util.List;
import java.util.Optional;

public interface ITradeService {

    public List<Trade> findAll() ;

    public Trade save(Trade trade) ;

    public Optional<Trade> findById(Integer id);

    public void delete(Trade trade) ;

}
