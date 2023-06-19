package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.IBidListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * Implementation of service for bidlist
 */
@Service
public class BidListService implements IBidListService {
    private static final Logger logger = LoggerFactory.getLogger(BidListService.class);

    @Autowired
    private BidListRepository bidListRepository;


    public List<BidList> findAll() {
        logger.debug("findAll() sollicité de BidListService");
        try{
            logger.info("findAll() effectuée de BidListService");
            return bidListRepository.findAll();
        }catch (Exception e){
            logger.error("Erreur au findAll() de BidListService", e);
            return null;
        }
    }

    public BidList save(BidList bid) {
        logger.debug("save() sollicité de BidListService");
        try {
            logger.info("save() effectuée de BidListService");
            return bidListRepository.save(bid);
        }catch (Exception e){
            logger.error("Erreur au save() de BidListService", e);
            return null;
        }
    }

    public Optional<BidList> findById(Integer id) {
        logger.debug("findById() sollicité de BidListService");
        try {
            logger.info("findById() effectuée de BidListService");
            return bidListRepository.findById(id);
        }catch (Exception e){
            logger.error("Erreur au findById() de BidListService", e);
            return null;
        }
    }

    public void delete(BidList bid) {
        logger.debug("delete() sollicité de BidListService");
        try {
            logger.info("delete() effectuée de BidListService");
            bidListRepository.delete(bid);
        }catch (Exception e){
            logger.error("Erreur au delete() de BidListService", e);

        }
    }
}
