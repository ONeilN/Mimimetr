package com.nugumanov.mimimetr.services;

import com.nugumanov.mimimetr.models.Pair;
import com.nugumanov.mimimetr.repositories.CatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.SessionScope;

/**
 * @author Aizat Nugumanov
 */
@Service
@Transactional(readOnly = true)
@SessionScope
public class VoteService {

    private final CatsRepository catsRepository;
    private final PairsService pairsService;
    private final GuestsService guestsService;

    @Autowired
    public VoteService(CatsRepository catsRepository, PairsService pairsService, GuestsService guestsService) {
        this.catsRepository = catsRepository;
        this.pairsService = pairsService;
        this.guestsService = guestsService;
    }

    @Transactional
    public void vote(int id, Pair pair) {
        catsRepository.findById(id).ifPresent(cat -> {
            int voices = cat.getVoices() + 1;
            cat.setVoices(voices);
        });

        guestsService.markPair(pair);
        pairsService.deletePair(pair);
    }
}
