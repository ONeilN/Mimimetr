package com.nugumanov.mimimetr.services;

import com.nugumanov.mimimetr.models.Cat;
import com.nugumanov.mimimetr.models.Pair;
import com.nugumanov.mimimetr.repositories.CatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Optional;

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

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void vote(int id, Pair pair) {

        Optional<Cat> tmpCat = catsRepository.findById(id);

        if (tmpCat.isPresent()) {
            int voices = tmpCat.get().getVoices() + 1;
            tmpCat.get().setVoices(voices);

            guestsService.markPair(pair);
            pairsService.deletePair(pair);
        }
    }
}
