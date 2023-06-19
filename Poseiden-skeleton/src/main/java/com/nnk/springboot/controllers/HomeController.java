package com.nnk.springboot.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * Implementation of the controller for home
 */
@Controller
public class HomeController
{
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping("/")
	public String home(Model model)	{
		logger.debug("home() sollicité de HomeController");
		try {
			logger.info("home() effectuée de HomeController");
			return "home";
		}catch (Exception e){
			logger.error("Erreur au home() de HomeController", e);
			return null;
		}

	}

	@RequestMapping("/admin/home")
	public String adminHome(Model model)	{
		logger.debug("adminHome() sollicité de HomeController");
		try {
			logger.info("adminHome() effectuée de HomeController");
			return "redirect:/bidList/list";
		}catch (Exception e){
			logger.error("Erreur au adminHome() de HomeController", e);
			return null;
		}
	}


}
