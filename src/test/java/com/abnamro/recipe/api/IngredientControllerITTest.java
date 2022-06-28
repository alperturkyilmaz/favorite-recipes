package com.abnamro.recipe.api;

import com.abnamro.recipe.TestUtils;
import com.abnamro.recipe.application.RecipesApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = RecipesApplication.class)
@AutoConfigureMockMvc
@Sql("classpath:test-data.sql")
class IngredientControllerITTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    IngredientService ingredientService;


    @Test
    void testCreateIngredientEndpoint() throws Exception {
        String name = "New Ingredient for test";
        this.mockMvc.perform(post("/api/ingredient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.asJsonString(IngredientCreateDto.builder()
                                .name(name)
                                .build())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name));
    }


    @Test
    void testUpdateIngredientEndpoint() throws Exception {
        Long ingredientId = 10L;
        String name = "Updated name";
        IngredientDto ingredientDto = ingredientService.getIngredient(ingredientId);
        this.mockMvc.perform(put("/api/ingredient/{id}", ingredientId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.asJsonString(IngredientUpdateDto.builder()
                                .name(name)
                                .build())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name));
    }

    @Test
    void testDeleteIngredientEndpoint() throws Exception {
        Long ingredientId = 1L;
        this.mockMvc.perform(delete("/api/ingredient/{id}", ingredientId))
                .andExpect(status().isOk());
    }


}