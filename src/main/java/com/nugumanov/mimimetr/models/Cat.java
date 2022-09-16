package com.nugumanov.mimimetr.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Aizat Nugumanov
 */
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
    @NotEmpty
    private String imgPath;

    @Column(name = "voices")
    private int voices;

    public Cat() {
    }

    public Cat(String name, String imgPath, int voices) {
        this.name = name;
        this.imgPath = imgPath;
        this.voices = voices;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public int getVoices() {
        return voices;
    }

    public void setVoices(int voices) {
        this.voices = voices;
    }
}
