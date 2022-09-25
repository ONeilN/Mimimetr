package com.nugumanov.mimimetr.controllers;

import com.nugumanov.mimimetr.models.Cat;
import com.nugumanov.mimimetr.models.Pair;
import com.nugumanov.mimimetr.services.CatsService;
import com.nugumanov.mimimetr.services.GuestsService;
import com.nugumanov.mimimetr.services.PairsService;
import com.nugumanov.mimimetr.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.SessionScope;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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
    private final PairsService pairsService;
    private final GuestsService guestsService;
    private Pair pair;

    @Autowired
    public VoteController(CatsService catsService, VoteService voteService, PairsService pairsService, GuestsService guestsService) {
        this.catsService = catsService;
        this.voteService = voteService;
        this.pairsService = pairsService;
        this.guestsService = guestsService;
    }

    @GetMapping
    public String index(@CookieValue(value = "mimimetr_guest_id", required = false) String cookie,
                        HttpServletResponse response,
                        Model model) {

        if (cookie == null) {
            guestsService.registerGuest();
            Cookie cookieForSet = new Cookie("mimimetr_guest_id",
                    guestsService.getGuest().getCookieValue());
            cookieForSet.setMaxAge(30 * 24 * 60 * 60);
            response.addCookie(cookieForSet);
        } else {
            guestsService.setGuest(cookie);
        }

        pairsService.createGuestList();

        if (pairsService.isPairsOver()) {
            return "redirect:/rating";
        }

        pair = pairsService.getRandomPair();
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

        if (pairsService.isPairsOver()) {
            return "redirect:/rating";
        }

        voteService.vote(id, pair);

        return "redirect:/vote";
    }
}
