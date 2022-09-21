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
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

/**
 * @author Aizat Nugumanov
 */
@Controller
@RequestMapping("/vote")
@SessionScope
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
        boolean created = voteService.createPairs();
        System.out.println("Is pairs list created? " + created);

        pair = voteService.getRandomPair();
        System.out.println(pair.getLeftCatID() + " " + pair.getRightCatID());
        List<Cat> cats = catsService.getCatsFromPair(pair);

        model.addAttribute("leftCat", cats.get(0));
        model.addAttribute("rightCat", cats.get(1));

        return "vote";
    }

    @GetMapping("/{id}")
    public String vote(@PathVariable("id") int id) {

        if (!pair.isContain(id)) {
            System.out.println("ID doesn't match any cat from the pair!");
            return "redirect:/vote";
        }

        voteService.vote(id);
        voteService.deletePair(pair);

        if (voteService.isPairsOver()) {
            return "redirect:/rating";
        } else {
            return "redirect:/vote";
        }
    }
}
