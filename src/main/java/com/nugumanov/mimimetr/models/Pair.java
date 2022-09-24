package com.nugumanov.mimimetr.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Aizat Nugumanov
 */

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Pair {

    private int leftCatID;

    private int rightCatID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair pair = (Pair) o;

        if ((leftCatID == pair.leftCatID && rightCatID == pair.rightCatID)
                || (leftCatID == pair.rightCatID && rightCatID == pair.leftCatID)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = leftCatID;
        result = 31 * result + rightCatID;
        return result;
    }

    public boolean isContain(int id) {
        return (leftCatID == id || rightCatID == id);
    }

    public void shuffle() {
        int tmp = leftCatID;
        leftCatID = rightCatID;
        rightCatID = tmp;
    }
}
