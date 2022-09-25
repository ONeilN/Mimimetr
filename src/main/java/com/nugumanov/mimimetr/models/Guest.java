package com.nugumanov.mimimetr.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aizat Nugumanov
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Guest")
public class Guest {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "guest_id_seq")
    private int id;

    @Column(name = "cookie_value")
    @NotEmpty
    private String cookieValue;

    @ManyToMany(mappedBy = "guestList", fetch = FetchType.EAGER)
    private List<Pair> pairList;

    public Guest(String cookieValue) {
        this.cookieValue = cookieValue;
    }

    public List<Pair> getPairList() {
        return (pairList != null) ? pairList : new ArrayList<>();
    }
}
