package com.nugumanov.mimimetr.services;

import com.nugumanov.mimimetr.models.Cat;
import com.nugumanov.mimimetr.models.Pair;
import com.nugumanov.mimimetr.repositories.CatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Arrays;
import java.util.List;

/**
 * @author Aizat Nugumanov
 */
@Service
@Transactional(readOnly = true)
@SessionScope
public class CatsService {

    private final CatsRepository catsRepository;

    @Autowired
    public CatsService(CatsRepository catsRepository) {
        this.catsRepository = catsRepository;
    }

    public List<Cat> getCatsFromPair(Pair pair) {
        return Arrays.asList(
                catsRepository.findById(pair.getCat1ID()).orElse(null),
                catsRepository.findById(pair.getCat2ID()).orElse(null)
        );
    }

    public List<Cat> getOrderedCats() {
        return catsRepository.findTop10ByOrderByVoicesDesc();
    }
}
