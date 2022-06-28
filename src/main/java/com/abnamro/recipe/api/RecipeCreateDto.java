package com.abnamro.recipe.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RecipeCreateDto {
    @NotNull
    private String name;

    @NotNull
    private Integer numberOfServings;

    private Boolean vegetarian = Boolean.FALSE;

    @NotEmpty
    private Set<RecipeIngredientCreateDto> ingredients;

    @NotNull
    private String instructions;
}
