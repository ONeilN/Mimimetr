package com.nugumanov.mimimetr.controllers;

import com.nugumanov.mimimetr.models.Cat;
import com.nugumanov.mimimetr.services.CatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

/**
 * @author Aizat Nugumanov
 */
@Controller
@RequestMapping("/add")
@SessionScope
public class CatAddingController {

    private final CatsService catsService;

    @Autowired
    public CatAddingController(CatsService catsService) {
        this.catsService = catsService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("cat", new Cat());

        return "add";
    }

    @PostMapping
    public String addCat(@ModelAttribute("cat") @Valid Cat cat,
                         @RequestParam("img") MultipartFile image,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add";
        }

        try {
            catsService.addCat(cat, image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/";
    }
}
