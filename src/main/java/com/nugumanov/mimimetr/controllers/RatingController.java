package com.nugumanov.mimimetr.controllers;

import com.nugumanov.mimimetr.services.CatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Aizat Nugumanov
 */
@Controller
@RequestMapping("/rating")
public class RatingController {

    private final CatsService catsService;

    @Autowired
    public RatingController(CatsService catsService) {
        this.catsService = catsService;
    }

    @GetMapping
    public String rating(Model model) {
        model.addAttribute("cats", catsService.getOrderedCats());

        return "rating";
    }
}
