package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.service.impl.RatingService;
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
 * Implementation of the controller for rating
 */
@Controller
public class RatingController {
    private static final Logger logger = LoggerFactory.getLogger(RatingController.class);

    @Autowired
    private RatingService ratingService;

    @RequestMapping("/rating/list")
    public String home(Model model) {
        logger.debug("home() sollicitée de RatingController");
        try {
            model.addAttribute("ratings", ratingService.findAll());
            logger.info("home() effectuée de RatingController");
            return "rating/list";
        }catch (Exception e){
            logger.error("Erreur au home() de RatingController", e);
            return null;
        }
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        logger.debug("addRatingForm() sollicitée de RatingController");
        try {
            logger.info("addRatingForm() effectuée de RatingController");
            return "rating/add";
        }catch (Exception e){
            logger.error("Erreur au addRatingForm() de RatingController", e);
            return null;
        }
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        logger.debug("validate() sollicitée de RatingController");
        try {
            if (!result.hasErrors()) {
                ratingService.save(rating);
                logger.info("validate() effectuée de RatingController");
                return "redirect:/rating/list";

            }
            logger.info("validate() non effectuée de RatingController");
            return "rating/add";
        }catch (Exception e){
            logger.error("Erreur au home() de RatingController", e);
            return null;
        }
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        logger.debug("showUpdateForm() sollicitée de RatingController");
        try {
            Rating rating = ratingService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id " + id));
            model.addAttribute("rating", rating);
            logger.info("showUpdateForm() effectuée de RatingController");
            return "rating/update";
        }catch (Exception e){
            logger.error("Erreur au showUpdateForm() de RatingController", e);
            return null;
        }
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
        logger.debug("home() sollicitée de RatingController");
        try {
            if (result.hasErrors()) {
                logger.info("updateRating() non effectuée de RatingController");
                return "rating/update";
            }
            rating.setId(id);
            ratingService.save(rating);
            logger.info("updateRating() effectuée de RatingController");
            return "redirect:/rating/list";
        }catch (Exception e){
            logger.error("Erreur au updateRating() de RatingController", e);
            return null;
        }
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        logger.debug("deleteRating() sollicitée de RatingController");
        try {
            Rating rating = ratingService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id " + id));
            ratingService.delete(rating);
            logger.info("deleteRating() effectuée de RatingController");
            return "redirect:/rating/list";
        }catch (Exception e){
            logger.error("Erreur au deleteRating() de RatingController", e);
            return null;
        }
    }
}
