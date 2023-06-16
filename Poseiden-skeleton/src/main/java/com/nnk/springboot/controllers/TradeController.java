package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.service.impl.TradeService;
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

@Controller
public class TradeController {
    private static final Logger logger = LoggerFactory.getLogger(TradeController.class);

    @Autowired
    private TradeService tradeService;

    @RequestMapping("/trade/list")
    public String home(Model model) {
        logger.debug("home() sollicitée de TradeController");
        try {
            model.addAttribute("trades", tradeService.findAll());
            logger.info("home() effectuée de TradeController");
            return "trade/list";
        }catch (Exception e){
            logger.error("Erreur au home() de TradeController", e);
            return null;
        }
    }

    @GetMapping("/trade/add")
    public String addUser(Trade bid) {
        logger.debug("addUser() sollicitée de TradeController");
        try{
            logger.info("addUser() effectuée de TradeController");
            return "trade/add";
        }catch (Exception e){
            logger.error("Erreur au addUser() de TradeController", e);
            return null;
        }
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        logger.debug("validate() sollicitée de TradeController");
        try {
            if (!result.hasErrors()) {
                tradeService.save(trade);
                logger.info("validate() effectuée de TradeController");
                return "redirect:/trade/list";

            }
            logger.info("validate() non effectuée de TradeController");
            return "trade/add";
        }catch (Exception e){
            logger.error("Erreur au home() de TradeController", e);
            return null;
        }
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        logger.debug("showUpdateForm() sollicitée de TradeController");
        try {
            Trade trade = tradeService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id " + id));
            model.addAttribute("trade", trade);
            logger.info("showUpdateForm() effectuée de TradeController");
            return "trade/update";
        }catch (Exception e){
            logger.error("Erreur au showUpdateForm() de TradeController", e);
            return null;
        }
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
        logger.debug("updateTrade() sollicitée de TradeController");
        try {
            if (result.hasErrors()) {
                logger.info("updateTrade() non effectuée de TradeController");
                return "trade/update";
            }
            trade.setTradeId(id);
            tradeService.save(trade);
            logger.info("updateTrade() effectuée de TradeController");
            return "redirect:/trade/list";
        }catch (Exception e){
            logger.error("Erreur au home() de TradeController", e);
            return null;
        }
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        logger.debug("deleteTrade() sollicitée de TradeController");
        try {
            Trade trade = tradeService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id " + id));
            tradeService.delete(trade);
            logger.info("deleteTrade() effectuée de TradeController");
            return "redirect:/trade/list";
        }catch (Exception e){
            logger.error("Erreur au deleteTrade() de TradeController", e);
            return null;
        }
    }
}
