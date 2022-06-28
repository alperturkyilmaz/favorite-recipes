package com.abnamro.recipe.api;

import com.abnamro.recipe.model.Ingredient;
import com.abnamro.recipe.model.Recipe;
import com.abnamro.recipe.model.RecipeIngredient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Objects;

@Mapper(uses = {IngredientMapper.class})
public interface RecipeIngredientMapper {
    RecipeIngredientMapper INSTANCE = Mappers.getMapper(RecipeIngredientMapper.class);

    @Mapping(source = "id.recipeId", target = "recipeId")
    @Mapping(source = "ingredient", target = "ingredient")
    RecipeIngredientDto toDto(RecipeIngredient item);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "recipe", target = "recipe")
    @Mapping(source = "createDto.ingredientId", target = "ingredient", qualifiedByName = "ingredientIdToEntity")
    RecipeIngredient toEntity(RecipeIngredientCreateDto createDto, Recipe recipe);

    @Named("recipeIdToEntity")
    static Recipe recipeIdToEntity(Long recipeId) {
        Recipe recipe = null;

        if (Objects.nonNull(recipeId)) {
            recipe = new Recipe();
            recipe.setId(recipeId);
        }
        return recipe;
    }

    @Named("ingredientIdToEntity")
    static Ingredient ingredientIdToEntity(Long ingredientId) {
        Ingredient ingredient = null;

        if (Objects.nonNull(ingredientId)) {
            ingredient = new Ingredient();
            ingredient.setId(ingredientId);
        }
        return ingredient;
    }
}
