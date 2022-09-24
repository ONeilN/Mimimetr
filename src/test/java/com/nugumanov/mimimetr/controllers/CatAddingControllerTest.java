package com.nugumanov.mimimetr.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Aizat Nugumanov
 */
@SpringBootTest
@AutoConfigureMockMvc
class CatAddingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void index() throws Exception {
        this.mockMvc
                .perform(get("/add"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Мимимиметр")))
                .andExpect(content().string(containsString("Заполните поле с кличкой котика, добавьте фотографию и нажмите \"Добавить!\"")));
    }
}