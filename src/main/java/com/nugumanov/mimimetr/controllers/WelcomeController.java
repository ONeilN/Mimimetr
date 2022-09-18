package com.nugumanov.mimimetr.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Aizat Nugumanov
 */
@Controller
@RequestMapping("/")
public class WelcomeController {

    @GetMapping
    public String welcome() {
        return "welcome";
    }
}
