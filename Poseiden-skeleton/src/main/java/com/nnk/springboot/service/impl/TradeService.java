package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.service.ITradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * Implementation of service for trade
 */
@Service
public class TradeService implements ITradeService {
    private static final Logger logger = LoggerFactory.getLogger(TradeService.class);

    @Autowired
    private TradeRepository tradeRepository;

    public List<Trade> findAll() {
        logger.debug("findAll() sollicité de TradeService");
        try{
            logger.info("findAll() effectuée de TradeService");
            return tradeRepository.findAll();
        }catch (Exception e){
            logger.error("Erreur au findAll() de TradeService", e);
            return null;
        }
    }

    public Trade save(Trade trade) {
        logger.debug("save() sollicité de TradeService");
        try{
            logger.info("save() effectuée de TradeService");
            return tradeRepository.save(trade);
        }catch (Exception e){
            logger.error("Erreur au save() de TradeService", e);
            return null;
        }
    }

    public Optional<Trade> findById(Integer id) {
        logger.debug("findById() sollicité de TradeService");
        try {
            logger.info("findById() effectuée de TradeService");
            return tradeRepository.findById(id);
        }catch (Exception e){
            logger.error("Erreur au findById() de TradeService", e);
            return null;
        }
    }

    public void delete(Trade trade) {
        logger.debug("delete() sollicité de TradeService");
        try {
            logger.info("delete() effectuée de TradeService");
            tradeRepository.delete(trade);
        }catch (Exception e){
            logger.error("Erreur au delete() de TradeService", e);
        }


    }
}
