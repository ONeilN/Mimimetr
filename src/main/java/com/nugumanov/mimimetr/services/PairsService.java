package com.nugumanov.mimimetr.services;

import com.nugumanov.mimimetr.models.Cat;
import com.nugumanov.mimimetr.models.Pair;
import com.nugumanov.mimimetr.repositories.PairsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Aizat Nugumanov
 */
@Service
@Transactional(readOnly = true)
@SessionScope
public class PairsService {

    private final PairsRepository pairsRepository;
    private final CatsService catsService;
    private List<Pair> pairList;

    @Autowired
    public PairsService(PairsRepository pairsRepository, CatsService catsService) {
        this.pairsRepository = pairsRepository;
        this.catsService = catsService;

        pairList = getAllPairs();
    }

    public List<Pair> getAllPairs() {
        return pairsRepository.findAll();
    }

    @Transactional
    public void createPairs(Cat cat) {
        List<Cat> cats = catsService.getAllCats();
        List<Pair> tmpPairs = new ArrayList<>();

        for (Cat c : cats) {
            if (c.getId() != cat.getId()) {
                tmpPairs.add(new Pair(cat.getId(), c.getId()));
            }
        }

        pairsRepository.saveAll(tmpPairs);
        pairList = getAllPairs();
    }
    public Pair getRandomPair() {
        Pair pair = pairList.get(new Random().nextInt(pairList.size()));

        if (new Random().nextBoolean())
            pair.shuffle();

        return pair;
    }

    @Transactional
    public void deletePair(Pair pair) {
        pairList.remove(pair);
    }

    public boolean isPairsOver() {
        return pairList.isEmpty();
    }
}
