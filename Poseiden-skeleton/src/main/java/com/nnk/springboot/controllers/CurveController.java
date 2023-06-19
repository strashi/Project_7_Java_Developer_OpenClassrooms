package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.service.impl.CurvePointService;
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
 * Implementation of the controller for curvePoint
 */
@Controller
public class CurveController {
    private static final Logger logger = LoggerFactory.getLogger(CurveController.class);

    @Autowired
    private CurvePointService curvePointService;

    @RequestMapping("/curvePoint/list")
    public String home(Model model){
        logger.debug("home() sollicitée de CurveController");
        try {
            model.addAttribute("curvePoints", curvePointService.findAll());
            logger.info("home() effectuée de CurveController");
            return "curvePoint/list";
        }catch (Exception e){
            logger.error("Erreur au home() de CurveController", e);
            return null;
        }
    }

    @GetMapping("/curvePoint/add")
    public String addCurveForm(CurvePoint bid) {
        logger.debug("addCurveForm() sollicitée de CurveController");
        try {
            logger.info("addCurveForm() effectuée de CurveController");
            return "curvePoint/add";
        }catch (Exception e){
            logger.error("Erreur au home() de CurveController", e);
            return null;
        }
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        logger.debug("home() sollicitée de CurveController");
        try {
            if (!result.hasErrors()) {
                curvePointService.save(curvePoint);
                logger.info("validate() effectuée de CurveController");
                return "redirect:/curvePoint/list";
            }
            logger.info("validate() non effectuée de CurveController");
            return "curvePoint/add";
        }catch (Exception e){
            logger.error("Erreur au validate() de CurveController", e);
            return null;
        }
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        logger.debug("showUpdateForm() sollicitée de CurveController");
        try{
            CurvePoint curvePoint = curvePointService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid id"+ id));
            model.addAttribute("curvePoint", curvePoint);
            logger.info("showUpdateForm() effectuée de CurveController");
            return "curvePoint/update";
        }catch (Exception e){
            logger.error("Erreur au showUpdateForm() de CurveController", e);
            return null;
        }
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateCurve(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
        logger.debug("updateCurve() sollicitée de CurveController");
        try {
            if (result.hasErrors()) {
                logger.info("updateCurve() non effectuée de CurveController");
                return "curvePoint/update";
            }
            curvePoint.setId(id);
            curvePointService.save(curvePoint);
            logger.info("updateCurve() effectuée de CurveController");
            return "redirect:/curvePoint/list";
        }catch (Exception e){
            logger.error("Erreur au updateCurve() de CurveController", e);
            return null;
        }

    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteCurve(@PathVariable("id") Integer id, Model model) {
        logger.debug("deleteCurve() sollicitée de CurveController");
        try {
            CurvePoint curvePoint = curvePointService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id" + id));
            curvePointService.delete(curvePoint);
            logger.info("deleteCurve() effectuée de CurveController");
            return "redirect:/curvePoint/list";
        }catch (Exception e){
            logger.error("Erreur au deleteCurve() de CurveController", e);
            return null;
        }
    }
}
