package com.abnamro.recipe.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity(name = "recipe_ingredient")
@Getter
@Setter
@EqualsAndHashCode(exclude = {"recipe", "ingredient"})
@NoArgsConstructor
public class RecipeIngredient {

    @EmbeddedId
    private RecipeIngredientId id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("recipeId")
    private Recipe recipe;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ingredientId")
    private Ingredient ingredient;

    public RecipeIngredient(Ingredient ingredient, Recipe recipe) {
        this.ingredient = ingredient;
        this.recipe = recipe;
        this.id = new RecipeIngredientId(recipe.getId(), ingredient.getId());
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
        if (Objects.isNull(id) && Objects.nonNull(recipe)) {
            this.id = new RecipeIngredientId(recipe.getId(), ingredient.getId());
        }
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
        if (Objects.isNull(id) && Objects.nonNull(ingredient)) {
            this.id = new RecipeIngredientId(recipe.getId(), ingredient.getId());
        }
    }

}
