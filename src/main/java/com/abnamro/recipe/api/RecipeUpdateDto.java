package com.abnamro.recipe.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RecipeUpdateDto {

    private String name;

    private Integer numberOfServings;

    private Boolean vegetarian;

    private String instructions;

    private Set<RecipeIngredientCreateDto> ingredients;
}
