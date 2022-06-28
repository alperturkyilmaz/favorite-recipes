package com.abnamro.recipe.api;

import com.abnamro.recipe.application.RecipesApplication;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.persistence.EntityNotFoundException;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = RecipesApplication.class)
@Sql("classpath:test-data.sql")

 class IngredientServiceITTest {

    @Autowired
    private IngredientService ingredientService;

    @Test
    void testGetIngredientById() {
        IngredientDto ingredientDto = ingredientService.getIngredient(3L);
        assertThat(ingredientDto.getId()).isEqualTo(3L);
    }

    @Test
    void testGetIngredientByIdThrowsException() {
        assertThrows(NoSuchElementException.class, () -> ingredientService.getIngredient(120l));
    }

    @Test
    void testCreateIngredient() {
        String name = "my best ingredient";
        IngredientCreateDto ingredientCreateDto = IngredientCreateDto.builder()
                .name(name)
                .build();
        IngredientDto ingredientDto = ingredientService.createIngredient(ingredientCreateDto);
        assertThat(ingredientDto.getId()).isNotNull();
        assertThat(ingredientDto.getName()).isEqualTo(name);
    }

    @Test
    void testUpdateIngredient() {
        IngredientUpdateDto ingredientUpdateDto = IngredientUpdateDto.builder().name("updated name").build();
        IngredientDto ingredientDto = ingredientService.updateIngredient(7L, ingredientUpdateDto);
        assertThat(ingredientDto.getId()).isEqualTo(7L);
        assertThat(ingredientDto.getName()).isEqualTo(ingredientUpdateDto.getName());
    }

    @Test
    void testDeleteIngredient() {
        ingredientService.deleteIngredient(8L);
        assertThrows(NoSuchElementException.class, () -> ingredientService.getIngredient(8L));
    }
}