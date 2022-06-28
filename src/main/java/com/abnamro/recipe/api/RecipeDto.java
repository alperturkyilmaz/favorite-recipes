package com.abnamro.recipe.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data

@ApiModel(description = "Class representing a recipe.")
public class RecipeDto {
    @ApiModelProperty(notes = "Unique identifier of the recipe. No two recipe can have the same id.", required = true)
    private Long id;

    @ApiModelProperty(notes = "Unique name of the recipe. No two recipe can have the same name.", required = true)
    private String name;

    @ApiModelProperty(notes = "Number of servings of the recipe, non-negative integer")
    @Min(1)
    private Integer numberOfServings;

    @ApiModelProperty(notes = "Vegetarian recipe")
    private Boolean vegetarian;

    @ApiModelProperty(notes = "Set of ingredients,content of the recipe")
    private Set<RecipeIngredientDto> ingredients;

    @ApiModelProperty(notes = "Instructions for preparation of the dish")
    private String instructions;
}
