package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.RatingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {
    private static final Logger logger = LoggerFactory.getLogger(RatingService.class);

    @Autowired
    private RatingRepository ratingRepository;

     public List<Rating> findAll() {
        logger.debug("findAll() sollicité de RatingService");
        try {
            logger.info("findAll() effectuée de RatingService");
            return ratingRepository.findAll();
        }catch (Exception e){
            logger.error("Erreur au findAll() de RatingService", e);
            return null;
        }
    }

    public Rating save(Rating rating) {
        logger.debug("save() sollicité de RatingService");
        try {
            logger.info("save() effectuée de RatingService");
            return ratingRepository.save(rating);
        }catch (Exception e){
            logger.error("Erreur au save() de RatingService", e);
            return null;
        }
    }

    public Optional<Rating> findById(Integer id) {
        logger.debug("findById() sollicité de RatingService");
       try {
            logger.info("findById() effectuée de RatingService");
            return ratingRepository.findById(id);
        }catch (Exception e) {
            logger.error("Erreur au findById() de RatingService", e);
            return null;
        }
    }

    public void delete(Rating rating) {
        logger.debug("delete() sollicité de RatingService");
        try {
            logger.info("delete() effectuée de RatingService");
            ratingRepository.delete(rating);
        }catch (Exception e) {
            logger.error("Erreur au delete() de RatingService", e);
        }

    }
}
