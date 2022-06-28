package com.abnamro.recipe;

import com.abnamro.recipe.api.RecipeCreateDto;
import com.abnamro.recipe.api.RecipeIngredientCreateDto;
import com.abnamro.recipe.model.Ingredient;
import com.abnamro.recipe.model.Recipe;
import com.abnamro.recipe.model.RecipeIngredient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class TestUtils {
    private static final Long BASE_ID = 100000L;

    private static final String RECIPE_NAME_PREFIX = "Recipe_";

    private static final String INGREDIENT_NAME_PREFIX = "Ingredient_";

    public static Recipe createRecipe(Integer suffix, int noOfIngredients) {
        Recipe recipe = Recipe.builder()
                .id(BASE_ID + suffix)
                .name(RECIPE_NAME_PREFIX + suffix)
                .numberOfServings(4)
                .build();
        recipe.setIngredients(IntStream.range(1, noOfIngredients + 1).mapToObj(TestUtils::createIngredient)
                .map(ingredient -> new RecipeIngredient(ingredient, recipe))
                .collect(Collectors.toSet()));
        return recipe;
    }

    public static RecipeCreateDto createRecipeCreateDto(Integer suffix, int noOfIngredients) {
        RecipeCreateDto recipeCreateDto = new RecipeCreateDto();
        recipeCreateDto.setName(RECIPE_NAME_PREFIX + suffix);
        recipeCreateDto.setNumberOfServings(4);
        recipeCreateDto.setInstructions(createRandomInstructions());

        recipeCreateDto.setIngredients(LongStream.range(1, noOfIngredients + 1)
                .mapToObj(RecipeIngredientCreateDto::new)
                .collect(Collectors.toSet()));
        return recipeCreateDto;
    }

    public static Ingredient createIngredient(Integer suffix) {
        Ingredient ingredient = Ingredient.builder()
                .id(BASE_ID + suffix)
                .name(INGREDIENT_NAME_PREFIX + suffix)
                .build();
        return ingredient;
    }

    public static String createRandomInstructions() {
        Lorem lorem = LoremIpsum.getInstance();
        return lorem.getWords(50, 100);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
