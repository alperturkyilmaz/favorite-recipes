package com.abnamro.recipe.api;

import com.abnamro.recipe.TestUtils;
import com.abnamro.recipe.application.RecipesApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = RecipesApplication.class)
@AutoConfigureMockMvc
@Sql("classpath:test-data.sql")
 class RecipeControllerITTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetRecipeEndpoint() throws Exception {
        this.mockMvc.perform(get("/api/recipe/{id}", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2));
    }

    @Test
    void testUpdateRecipeEndpoint() throws Exception {
        Long recipeId = 3L;
        Long newIngredientId = 9L;
        String name = "Update-Recipe-1";
        RecipeUpdateDto updateDto = new RecipeUpdateDto();
        updateDto.setName(name);
        updateDto.setNumberOfServings(10);
        updateDto.setIngredients(Collections.singleton(new RecipeIngredientCreateDto(newIngredientId)));

        this.mockMvc.perform(put("/api/recipe/{id}", recipeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.asJsonString(updateDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.id").value(recipeId))
                .andExpect(jsonPath("$.ingredients", hasSize(1)))
                .andExpect(jsonPath("$.ingredients[0].ingredient.id").value(newIngredientId));
    }


    @Test
    void testDeleteRecipeEndpoint() throws Exception {
        Long recipeId = 2L;
        this.mockMvc.perform(get("/api/recipe/{id}", recipeId))
                .andExpect(status().isOk());
        this.mockMvc.perform(delete("/api/recipe/{id}", recipeId))
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/api/recipe/{id}", recipeId))
                .andExpect(status().isNotFound());
    }

    @Test
    void testFilter_Vegetarian_NumberOfServings_RecipeEndpoint() throws Exception {
        this.mockMvc.perform(get("/api/recipe")
                        .queryParam("vegetarian", "false")
                        .queryParam("numberOfServings", "7"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(3));
    }

    @Test
    void testFilter_IncludeIngredients_RecipeEndpoint() throws Exception {
        this.mockMvc.perform(get("/api/recipe")
                        .queryParam("include", "Ingredient-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void testFilter_IncludeIngredients_Instructions_RecipeEndpoint() throws Exception {
        this.mockMvc.perform(get("/api/recipe")
                        .queryParam("include", "Ingredient-1")
                        .queryParam("instructions", "keyword7"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void testFilter_IncludeIngredients_Instructions_EmptyResultRecipeEndpoint() throws Exception {
        this.mockMvc.perform(get("/api/recipe")
                        .queryParam("include", "Ingredient-12")
                        .queryParam("instructions", "keyword7"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void testFilter_IncludeIngredients_Instructions_Vegetarian_RecipeEndpoint() throws Exception {
        this.mockMvc.perform(get("/api/recipe")
                        .queryParam("include", "Ingredient-4")
                        .queryParam("instructions", "keyword2")
                        .queryParam("vegetarian", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }
}