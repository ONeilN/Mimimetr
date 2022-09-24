package com.nugumanov.mimimetr.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Aizat Nugumanov
 */
class PairTest {

    @Test
    void isContain() {
        Pair testPair = new Pair(1, 2);

        int testId1 = 1;
        int testId2 = 2;
        int testId3 = 3;

        assertTrue(testPair.isContain(testId1));
        assertTrue(testPair.isContain(testId2));
        assertFalse(testPair.isContain(testId3));
    }

    @Test
    void shuffle() {
        Pair testPair = new Pair(1, 2);
        testPair.shuffle();
        assertEquals(1, testPair.getRightCatID());
        assertEquals(2, testPair.getLeftCatID());
    }
}