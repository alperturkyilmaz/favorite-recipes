package com.abnamro.recipe.api;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class RecipeIngredientDto {

    private Long recipeId;

    private IngredientDto ingredient;

}
