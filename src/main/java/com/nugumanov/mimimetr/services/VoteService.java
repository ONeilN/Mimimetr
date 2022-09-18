package com.nugumanov.mimimetr.services;

import com.nugumanov.mimimetr.models.Cat;
import com.nugumanov.mimimetr.models.Pair;
import com.nugumanov.mimimetr.repositories.CatsRepository;
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
    private final List<Pair> pairs;

    @Autowired
    public VoteService(CatsRepository catsRepository) {
        this.catsRepository = catsRepository;
        pairs = new ArrayList<>();
    }

    @Transactional
    public void vote(int id) {
        catsRepository.findById(id).ifPresent(cat -> {
            int voices = cat.getVoices() + 1;
            cat.setVoices(voices);
        });
    }

    public boolean createPairs() {

        if (!pairs.isEmpty()) {
            return false;
        }

        List<Cat> cats = catsRepository.findAll();

        for (Cat leftCat : cats) {
            for (Cat rightCat : cats) {
                if (leftCat.getId() != rightCat.getId()) {
                    Pair tmpPair = new Pair(leftCat.getId(), rightCat.getId());

                    if (!pairs.contains(tmpPair)) {
                        pairs.add(tmpPair);
                    }
                }

            }
        }

        for (Pair pair : pairs) {
            System.out.println(pair);
        }

        return true;
    }

    public Pair getRandomPair() {
        int randomIndex = (int) (Math.random() * pairs.size());
        return pairs.get(randomIndex);
    }

    public void deletePair(Pair pair) {
        pairs.remove(pair);
    }

    public boolean isPairsOver() {
        return pairs.isEmpty();
    }
}
