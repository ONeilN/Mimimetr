package com.nugumanov.mimimetr.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author Aizat Nugumanov
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Pair")
public class Pair {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "left_cat_id")
    private int leftCatID;

    @Column(name = "right_cat_id")
    private int rightCatID;

    @ManyToMany
    @JoinTable(
            name = "Pair_Guest",
            joinColumns = @JoinColumn(name = "pair_id"),
            inverseJoinColumns = @JoinColumn(name = "guest_id")
    )
    private List<Guest> guestList;

    public Pair(int leftCatID, int rightCatID) {
        this.leftCatID = leftCatID;
        this.rightCatID = rightCatID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair pair = (Pair) o;

        return (leftCatID == pair.leftCatID && rightCatID == pair.rightCatID)
                || (leftCatID == pair.rightCatID && rightCatID == pair.leftCatID);
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
