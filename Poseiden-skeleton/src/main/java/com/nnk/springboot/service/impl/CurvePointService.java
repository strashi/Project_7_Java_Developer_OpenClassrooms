package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.service.ICurvePointService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * Implementation of service for curvePoint
 */
@Service
public class CurvePointService implements ICurvePointService {
    private static final Logger logger = LoggerFactory.getLogger(CurvePointService.class);

    @Autowired
    private CurvePointRepository curvePointRepository;

    public List<CurvePoint> findAll() {
        logger.debug("findAll() sollicité de CurvePointService");
        try {
            logger.info("findAll() effectuée de CurvePointService");
            return curvePointRepository.findAll();
        }catch (Exception e){
            logger.error("Erreur au findAll() de CurvePointService", e);
            return null;
        }

    }

    public CurvePoint save(CurvePoint curvePoint) {
        logger.debug("save() sollicité de CurvePointService");
        try{
            logger.info("save() effectuée de CurvePointService");
            return curvePointRepository.save(curvePoint);
        }catch (Exception e){
            logger.error("Erreur au save() de CurvePointService", e);
            return null;
        }

    }

    public Optional<CurvePoint> findById(Integer id) {
        logger.debug("findById() sollicité de CurvePointService");
        try{
            logger.info("findById() effectuée de CurvePointService");
            return curvePointRepository.findById(id);
        }catch (Exception e){
            logger.error("Erreur au findById() de CurvePointService", e);
            return null;
        }
    }

    public void delete(CurvePoint curvePoint) {
        logger.debug("delete() sollicité de CurvePointService");
        try{
            logger.info("delete() effectuée de CurvePointService");
            curvePointRepository.delete(curvePoint);
        }catch (Exception e){
            logger.error("Erreur au delete() de CurvePointService", e);
        }
    }
}
