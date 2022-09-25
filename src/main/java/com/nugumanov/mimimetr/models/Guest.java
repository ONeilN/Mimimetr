package com.nugumanov.mimimetr.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "cookie_value")
    @NotEmpty
    private String cookieValue;

    @ManyToMany(mappedBy = "guestList")
    private List<Pair> pairList;

    public Guest(String cookieValue) {
        this.cookieValue = cookieValue;
    }
}
