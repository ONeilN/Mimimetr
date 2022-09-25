package com.nugumanov.mimimetr.services;

import com.nugumanov.mimimetr.models.Pair;
import com.nugumanov.mimimetr.repositories.CatsRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aizat Nugumanov
 */
@Service
@Transactional(readOnly = true)
@SessionScope
public class VoteService {

    private final CatsRepository catsRepository;
    private final PairsService pairsService;
    @Getter
    private final List<Pair> pairs;

    @Autowired
    public VoteService(CatsRepository catsRepository, PairsService pairsService) {
        this.catsRepository = catsRepository;
        this.pairsService = pairsService;
        pairs = new ArrayList<>();
    }

    @Transactional
    public void vote(int id, Pair pair) {
        catsRepository.findById(id).ifPresent(cat -> {
            int voices = cat.getVoices() + 1;
            cat.setVoices(voices);
        });

        pairsService.deletePair(pair);
    }
}
