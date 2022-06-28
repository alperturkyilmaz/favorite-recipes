package com.abnamro.recipe.api;

import com.abnamro.recipe.model.Recipe;
import com.querydsl.core.types.Predicate;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


@RestController
@RequiredArgsConstructor
@Api("Set of endpoints for Creating, Retrieving, Updating and Deleting of Recipe.")
public class RecipeController {
    private final RecipeService recipeService;


    @GetMapping("/api/recipe")
    @ApiOperation("Returns list of filtered recipes based on one or more of the criteria")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataTypeClass = Long.class, example = "1", value = "id of the Recipe", paramType = "query"),
            @ApiImplicitParam(name = "name", dataTypeClass = String.class, value = "name of the Recipe", paramType = "query", allowMultiple = true),
            @ApiImplicitParam(name = "vegetarian", dataTypeClass = Boolean.class, value = "Whether or not the dish is vegetarian", paramType = "query"),
            @ApiImplicitParam( name = "numberOfServings", dataTypeClass = Integer.class, example = "1",value = "The number of servings, non-negative integer", paramType = "query"),
            @ApiImplicitParam(name = "instructions", dataTypeClass = String.class, value = "Text search within the instructions.", paramType = "query"),
            @ApiImplicitParam(name = "include", dataTypeClass = String.class, value = "Specific ingredients to be included", paramType = "query", allowMultiple = true),
    })
    public List<RecipeDto> getRecipes(@ApiIgnore @QuerydslPredicate(root = Recipe.class,
            bindings = RecipeQuerydslBinderCustomizer.class) Predicate predicate) {
        return recipeService.getRecipes(predicate);
    }


    @GetMapping("/api/recipe/{id}")
    @ApiOperation("Returns a specific recipe by their identifier. 404 if does not exist.")
    public RecipeDto getRecipe(@ApiParam("Id of the recipe to be obtained. Cannot be empty.")
                               @PathVariable("id") @NotNull Long id) {
        return recipeService.getRecipe(id);
    }

    @PostMapping("/api/recipe")
    @ApiOperation("Creates a new recipe.")
    public RecipeDto createRecipe(@ApiParam("Information for a new recipe to be created.")
                                  @Valid @RequestBody RecipeCreateDto recipeCreateDto) {
        return recipeService.createRecipe(recipeCreateDto);
    }

    @DeleteMapping("/api/recipe/{id}")
    @ApiOperation("Deletes an recipe from the system. 404 if the recipe's identifier is not found.")
    public void deleteRecipe(@ApiParam("Id of the recipe to be obtained. Cannot be empty.")
                             @PathVariable("id") @NotNull Long id) {
        recipeService.deleteRecipe(id);
    }

    @PutMapping("/api/recipe/{id}")
    @ApiOperation("Updates the existing recipe. 404 if the ingredient's identifier is not found.")
    public RecipeDto updateRecipe(@ApiParam("Id of the recipe to be updated. Cannot be empty.")
                                  @PathVariable("id") @NotNull Long id,
                                  @ApiParam("Information for the recipe to be updated.")
                                  @NotNull @RequestBody RecipeUpdateDto recipeUpdateDto) {
        return recipeService.updateRecipe(id, recipeUpdateDto);
    }


}
