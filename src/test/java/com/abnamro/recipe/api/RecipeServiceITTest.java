package com.abnamro.recipe.api;

import com.abnamro.recipe.TestUtils;
import com.abnamro.recipe.application.RecipesApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = RecipesApplication.class)
@Sql("classpath:test-data.sql")
class RecipeServiceITTest {

    @Autowired
    RecipeService recipeService;

    @Test
    void testGetRecipeById() {
        RecipeDto recipeDto = recipeService.getRecipe(1L);

        assertThat(recipeDto.getName()).isEqualTo("Recipe-1");
        assertThat(recipeDto.getId()).isNotNull();
        assertThat(recipeDto.getId()).isEqualTo(1L);
    }

    @Test
    void testGetRecipeByIdThrowsException() {
        assertThrows(NoSuchElementException.class, () -> recipeService.getRecipe(Long.MAX_VALUE));
    }

    @Test
    @Sql("classpath:test-data.sql")
    void testUpdateRecipe() {
        Long recipeId = 1L;
        RecipeDto recipeDto = recipeService.getRecipe(recipeId);
        Set<RecipeIngredientCreateDto> recipeIngredientCreateDtoSet = recipeDto.getIngredients().stream()
                .map(RecipeIngredientDto::getIngredient)
                .map(IngredientDto::getId)
                .map(RecipeIngredientCreateDto::new)
                .collect(Collectors.toSet());

        recipeIngredientCreateDtoSet.add(new RecipeIngredientCreateDto(3L));
        RecipeUpdateDto recipeUpdateDto = new RecipeUpdateDto();
        recipeUpdateDto.setName("Update-Recipe-1");
        recipeUpdateDto.setNumberOfServings(10);
        recipeUpdateDto.setIngredients(recipeIngredientCreateDtoSet);
        RecipeDto updatedRecipeDto = recipeService.updateRecipe(recipeId, recipeUpdateDto);
        assertThat(updatedRecipeDto.getName()).isEqualTo("Update-Recipe-1");
        assertThat(updatedRecipeDto.getNumberOfServings()).isEqualTo(10);
        assertThat(updatedRecipeDto.getIngredients()).hasSize(recipeIngredientCreateDtoSet.size());


    }

    @Test
    void testDeleteRecipe() {
        recipeService.deleteRecipe(1L);
        assertThrows(NoSuchElementException.class, () -> recipeService.getRecipe(1L));
    }

    @Test
    void testCreateRecipe() {
        String name = "my best ingredient";
        String instructions = TestUtils.createRandomInstructions();
        RecipeCreateDto recipeCreateDto = RecipeCreateDto.builder()
                .name("recipe_name")
                .numberOfServings(20)
                .instructions(instructions)
                .ingredients(Collections.singleton(RecipeIngredientCreateDto.builder().ingredientId(7L).build()))
                .build();
        RecipeDto recipeDto = recipeService.createRecipe(recipeCreateDto);
        assertThat(recipeDto.getId()).isNotNull();

        assertThat(recipeDto)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .comparingOnlyFields("numberOfServings", "vegetarian", "instructions")
                .isEqualTo(recipeCreateDto);
        assertThat(recipeDto.getIngredients()).hasSize(1);

    }
}
