package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.service.impl.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
/**
 * Implementation of the controller for user
 */

@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    @RequestMapping("/user/list")
    public String home(Model model) {
        logger.debug("home() sollicitée de UserController");
        try {
            model.addAttribute("users", userService.findAll());
            logger.info("home() effectuée de UserController");
            return "user/list";
        } catch (Exception e) {
            logger.error("Erreur au home() de UserController", e);
            return null;
        }
    }

    @GetMapping("/user/add")
    public String addUser(User bid) {
        logger.debug("addUser() sollicitée de UserController");
        try {
            logger.info("addUser() effectuée de UserController");
            return "user/add";
        } catch (Exception e) {
            logger.error("Erreur au addUser() de UserController", e);
            return null;
        }
    }

    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model) {
        logger.debug("validate() sollicitée de UserController");
        try {
            if (!result.hasErrors()) {
                userService.save(user);
                logger.info("validate() effectuée de UserController");
                return "redirect:/user/list";
            }
            logger.info("validate() non effectuée de UserController");
            return "user/add";
        } catch (Exception e) {
            logger.error("Erreur au validate() de UserController", e);
            return null;
        }
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        logger.debug("showUpdateForm() sollicitée de UserController");
        try {
            User user = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
            user.setPassword("");
            model.addAttribute("user", user);
            logger.info("showUpdateForm() effectuée de UserController");
            return "user/update";
        } catch (Exception e) {
            logger.error("Erreur au showUpdateForm() de UserController", e);
            return null;
        }
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
                             BindingResult result, Model model) {
        logger.debug("updateUser() sollicitée de UserController");
        try {
            if (result.hasErrors()) {
                logger.info("updateUser() non effectuée de UserController");
                return "user/update";
            }

            user.setId(id);
            userService.save(user);
            logger.info("updateUser() effectuée de UserController");
            return"redirect:/user/list";
        } catch (Exception e) {
            logger.error("Erreur au updateUser() de UserController", e);
            return null;
        }
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        logger.debug("deleteUser() sollicitée de UserController");
        try {
            User user = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
            userService.delete(user);
            logger.info("deleteUser() effectuée de UserController");
            return "redirect:/user/list";
        } catch (Exception e) {
            logger.error("Erreur au deleteUser() de UserController", e);
            return null;
        }
    }
}
