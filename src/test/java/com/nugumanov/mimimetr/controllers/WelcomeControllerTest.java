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
class WelcomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void welcome() throws Exception {
        this.mockMvc
                .perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Добро пожаловать!")))
                .andExpect(content().string(containsString("Это конкурс за звание самого мимимишного котика! Если хотите проголосовать, нажмите кнопку ниже)")));
    }
}