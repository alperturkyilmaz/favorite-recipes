package com.abnamro.recipe.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(description = "Class representing an ingredient.")
public class IngredientDto {
    @ApiModelProperty(notes = "Unique identifier of the ingredient. No two ingredient can have the same id.",  required = true)
    private Long id;

    @ApiModelProperty(notes = "Unique name of the ingredient. No two ingredient can have the same name.", required = true)
    private String name;

}
