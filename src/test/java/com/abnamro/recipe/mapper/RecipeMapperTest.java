package com.abnamro.recipe.mapper;

import com.abnamro.recipe.TestUtils;
import com.abnamro.recipe.api.*;
import com.abnamro.recipe.model.Recipe;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class RecipeMapperTest {

    private final RecipeMapper mapper = RecipeMapper.INSTANCE;


    @Test
    void testEntityToDto() {
        Recipe recipe = TestUtils.createRecipe(2, 2);
        RecipeDto recipeDto = mapper.toDto(recipe);
        assertThat(recipe).usingRecursiveComparison().ignoringCollectionOrder().comparingOnlyFields("name", "id").isEqualTo(recipeDto);

    }

    @Test
    void testCreateDtoToEntity() {

        RecipeCreateDto recipeCreateDto = TestUtils.createRecipeCreateDto(3, 4);
        Recipe recipe = RecipeMapper.INSTANCE.toEntity(recipeCreateDto);
        assertThat(recipe).usingRecursiveComparison().ignoringFields("id").comparingOnlyFields("name", "numberOfServings", "vegetarian", "instructions").isEqualTo(recipeCreateDto);

    }

    @Test
    void testUpdateDtoToEntity() {
        Recipe recipe = TestUtils.createRecipe(2, 2);
        RecipeUpdateDto recipeUpdateDto = new RecipeUpdateDto();
        recipeUpdateDto.setNumberOfServings(2);
        recipeUpdateDto.setVegetarian(true);
        RecipeIngredientCreateDto recipeIngredientCreateDto = new RecipeIngredientCreateDto();
        recipeIngredientCreateDto.setIngredientId(6L);
        recipeUpdateDto.setIngredients(Collections.singleton(recipeIngredientCreateDto));


        RecipeMapper.INSTANCE.toUpdatedEntity(recipe, recipeUpdateDto);
        assertThat(recipe).usingRecursiveComparison().ignoringFields("id").comparingOnlyFields("numberOfServings", "vegetarian", "instructions").isEqualTo(recipeUpdateDto);

    }


}