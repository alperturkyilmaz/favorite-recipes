package com.abnamro.recipe.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
@Api("Set of endpoints for Creating, Retrieving, Updating and Deleting of Ingredients.")
public class IngredientController {
    private final IngredientService ingredientService;

    @GetMapping("/api/ingredient/{id}")
    @ApiOperation("Returns a specific ingredient by their identifier. 404 if does not exist.")
    public IngredientDto getIngredient(@ApiParam("Id of the Ingredient to be obtained. Cannot be empty.")
                                       @PathVariable("id") @NotNull Long id) {
        return ingredientService.getIngredient(id);
    }

    @PostMapping("/api/ingredient")
    @ApiOperation("Creates a new ingredient.")
    public IngredientDto createIngredient(@ApiParam("Information for a new ingredient to be created.")
                                          @Valid @RequestBody IngredientCreateDto ingredientCreateDto) {
        return ingredientService.createIngredient(ingredientCreateDto);
    }

    @DeleteMapping("/api/ingredient/{id}")
    @ApiOperation("Deletes an ingredient from the system. 404 if the ingredient's identifier is not found.")
    public void deleteIngredient(@ApiParam("Id of the ingredient to be deleted. Cannot be empty.")
                                 @PathVariable("id") @NotNull Long id) {
        ingredientService.deleteIngredient(id);
    }

    @PutMapping("/api/ingredient/{id}")
    @ApiOperation("Updates the existing ingredient.404 if the ingredient's identifier is not found.")
    public IngredientDto updateIngredient(@ApiParam("Id of the ingredient to be updated. Cannot be empty.")
                                          @PathVariable("id") @NotNull Long id,
                                          @ApiParam("Information for the ingredient to be updated.")
                                          @NotNull @RequestBody IngredientUpdateDto ingredientUpdateDto) {
        return ingredientService.updateIngredient(id, ingredientUpdateDto);
    }


}
