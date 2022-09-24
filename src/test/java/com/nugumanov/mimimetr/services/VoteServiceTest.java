package com.nugumanov.mimimetr.services;

import com.nugumanov.mimimetr.models.Cat;
import com.nugumanov.mimimetr.models.Pair;
import com.nugumanov.mimimetr.repositories.CatsRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * @author Aizat Nugumanov
 */
@SpringBootTest
@AutoConfigureMockMvc
class VoteServiceTest {

    @Mock
    private CatsRepository catsRepository;
    private VoteService voteService;
    private static List<Cat> testCats;

    @BeforeAll
    public static void prepareTestData() {
        testCats = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Cat tmpCat = new Cat("Cat_" + i, "cat_" + i + ".jpg", i);
            tmpCat.setId(i);
            testCats.add(tmpCat);
        }
    }

    @BeforeEach
    public void init() {
        voteService = new VoteService(catsRepository);
    }

    @Test
    void vote_PairsIsEmpty() {
        when(catsRepository.findAll()).thenReturn(testCats);

        boolean isCreated = voteService.createPairs();
        List<Pair> resultPairs = voteService.getPairs();

        assertNotNull(resultPairs);
        assertEquals(3, resultPairs.size());
        assertTrue(isCreated);
    }

    @Test
    void vote_PairsIsNotEmpty() {
        when(catsRepository.findAll()).thenReturn(testCats);
        boolean tmp = voteService.createPairs();

        boolean isCreated = voteService.createPairs();
        List<Pair> resultPairs = voteService.getPairs();

        assertNotNull(resultPairs);
        assertFalse(isCreated);
    }
}