package com.nugumanov.mimimetr.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Aizat Nugumanov
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Cat")
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 30, message = "Длина имени должна быть от 2 до 30 символов")
    private String name;

    @Column(name = "img_path")
    @NotEmpty(message = "Должно быть изображение кошки")
    private String imgPath;

    @Column(name = "voices")
    private int voices;

    public Cat(String name, String imgPath, int voices) {
        this.name = name;
        this.imgPath = imgPath;
        this.voices = voices;
    }
}
