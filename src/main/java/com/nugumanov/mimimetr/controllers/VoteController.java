package com.nugumanov.mimimetr.controllers;

import com.nugumanov.mimimetr.models.Cat;
import com.nugumanov.mimimetr.models.Pair;
import com.nugumanov.mimimetr.services.VoteService;
import com.nugumanov.mimimetr.services.CatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Aizat Nugumanov
 */
@Controller
@RequestMapping("/vote")
public class VoteController {

    private final CatsService catsService;
    private final VoteService voteService;
    private Pair pair;

    @Autowired
    public VoteController(CatsService catsService, VoteService voteService) {
        this.catsService = catsService;
        this.voteService = voteService;
    }

    @GetMapping
    public String index(Model model) {
        pair = voteService.getRandomPair();
        List<Cat> cats = catsService.getCatsFromPair(pair);
        System.out.println(pair.getCat1ID() + " " + pair.getCat2ID());
        model.addAttribute("leftCat", cats.get(0));
        model.addAttribute("rightCat", cats.get(1));

        return "vote";
    }

    @GetMapping("/{id}")
    public String vote(@PathVariable("id") int id) {
        voteService.vote(id);
        voteService.deletePair(pair);

        if (voteService.isPairsOver()) {
            return "rating";
        } else {
            return "redirect:/vote";
        }
    }
}
