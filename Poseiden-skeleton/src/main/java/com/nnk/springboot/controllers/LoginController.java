package com.nnk.springboot.controllers;

import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.impl.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
/**
 * Implementation of the controller for login
 */
@Controller
@RequestMapping("app")
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @GetMapping("login")
    public ModelAndView login() {
        logger.debug("login() sollicitée de LoginController");
        try {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("login");
            logger.info("login() effectuée de LoginController");
            return mav;
        }catch (Exception e){
            logger.error("Erreur au login() de LoginController", e);
            return null;
        }
    }

    @GetMapping("secure/article-details")
    public ModelAndView getAllUserArticles() {
        logger.debug("getAllUserArticles() sollicitée de LoginController");
        try {
            ModelAndView mav = new ModelAndView();
            mav.addObject("users", userService.findAll());
            mav.setViewName("user/list");
            logger.info("getAllUserArticles() effectuée de LoginController");
            return mav;
        }catch (Exception e){
            logger.error("Erreur au getAllUserArticles() de LoginController", e);
            return null;
        }
    }

    @GetMapping("error")
    public ModelAndView error() {
        logger.debug("error() sollicitée de LoginController");
        try {
            ModelAndView mav = new ModelAndView();
            String errorMessage = "You are not authorized for the requested data.";
            mav.addObject("errorMsg", errorMessage);
            mav.setViewName("403");
            logger.info("error() effectuée de LoginController");
            return mav;
        }catch (Exception e){
            logger.error("Erreur au error() de LoginController", e);
            return null;
        }
    }
}
