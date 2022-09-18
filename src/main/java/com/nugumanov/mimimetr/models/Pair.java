package com.nugumanov.mimimetr.models;

/**
 * @author Aizat Nugumanov
 */

public class Pair {

    private int cat1ID;

    private int cat2ID;

    public Pair() {
    }

    public Pair(int cat1ID, int cat2ID) {
        this.cat1ID = cat1ID;
        this.cat2ID = cat2ID;
    }

    public int getCat1ID() {
        return cat1ID;
    }

    public void setCat1ID(int cat1ID) {
        this.cat1ID = cat1ID;
    }

    public int getCat2ID() {
        return cat2ID;
    }

    public void setCat2ID(int cat2ID) {
        this.cat2ID = cat2ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair pair = (Pair) o;

        if ((cat1ID == pair.cat1ID && cat2ID == pair.cat2ID)
                || (cat1ID == pair.cat2ID && cat2ID == pair.cat1ID)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isContain(int id) {
        return (cat1ID == id || cat2ID == id);
    }

    @Override
    public int hashCode() {
        int result = cat1ID;
        result = 31 * result + cat2ID;
        return result;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "cat1ID=" + cat1ID +
                ", cat2ID=" + cat2ID +
                '}';
    }
}
