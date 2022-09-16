package com.nugumanov.mimimetr.controllers;

import com.nugumanov.mimimetr.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Aizat Nugumanov
 */
@Controller
@RequestMapping("/")
public class WelcomeController {

    private final VoteService voteService;

    @Autowired
    public WelcomeController(VoteService voteService) {
        this.voteService = voteService;
    }

    @GetMapping
    public String welcome() {
         voteService.createPairs();

        return "welcome";
    }
}
