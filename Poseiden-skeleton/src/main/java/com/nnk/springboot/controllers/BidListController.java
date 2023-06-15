package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.impl.BidListService;
import com.nnk.springboot.service.impl.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;


@Controller
public class BidListController {
    private static final Logger logger = LoggerFactory.getLogger(BidListController.class);

    @Autowired
    private BidListRepository bidListRepository;
    @Autowired
    private BidListService bidListService;

    @RequestMapping("/bidList/list")
    public String home(Model model){
        logger.debug("home() sollicitée de BidListController");
        try {
            model.addAttribute("bidLists", bidListService.findAll());
            logger.info("home() effectuée de BidListController");
            return "bidList/list";
        }catch (Exception e){
            logger.error("Erreur au home() de BidListController", e);
            return null;
        }
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        logger.debug("addBidForm() sollicitée de BidListController");
        try {
            logger.info("addBidForm() effectuée de BidListController");
            return "bidList/add";
        }catch (Exception e){
            logger.error("Erreur au addBidForm() de BidListController", e);
            return null;
        }
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        logger.debug("validate() sollicité de BidListController");
        try {
            if (!result.hasErrors()) {
                bidListService.save(bid);
                logger.info("validate() effectuée de BidListController");
                return "redirect:/bidList/list";
            }

            logger.info("validate() non effectuée de BidListController");
            return "bidList/add";
        }catch (Exception e){
            logger.error("Erreur au validate() de BidListController", e);
            return null;
        }
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        logger.debug("showUpdateForm() sollicité de BidListController");
        try {
            BidList bid = bidListService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid BidListId"+ id));
            model.addAttribute("bidList", bid);
            logger.info("showUpdateForm() effectuée de BidListController");
            return "bidList/update";
        }catch (Exception e){
            logger.error("Erreur au showUpdateForm() de BidListController", e);
            return null;
        }

    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
        logger.debug("updateBid() sollicité de BidListController");
        try {
            if(result.hasErrors()){
                logger.info("updateBid() effectuée de BidListController");
                return "bidList/update";
            }
            bidList.setBidListId(id);
            bidListService.save(bidList);
            logger.info("updateBid() non effectuée de BidListController");
            return "redirect:/bidList/list";
        }catch (Exception e){
            logger.error("Erreur au updateBid() de BidListController", e);
            return null;
        }

    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        logger.debug("deleteBid() sollicité de BidListController");
        try {
            BidList bid = bidListService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid BidListId"+ id));
            bidListService.delete(bid);
            logger.info("deleteBid() effectuée de BidListController");
            return "redirect:/bidList/list";
        }catch (Exception e){
            logger.error("Erreur au deleteBid() de BidListController", e);
            return null;
        }

    }
}
