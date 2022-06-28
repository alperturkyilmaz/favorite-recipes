package com.abnamro.recipe.api;

import com.abnamro.recipe.model.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper(uses = {RecipeIngredientMapper.class})
public interface RecipeMapper {
    RecipeMapper INSTANCE = Mappers.getMapper(RecipeMapper.class);

    RecipeDto toDto(Recipe item);

    List<RecipeDto> toDto(Iterable<Recipe> item);

    @Mapping(source = "ingredients", target = "ingredients", ignore = true)
    Recipe toEntity(RecipeCreateDto item);


    default void toUpdatedEntity(Recipe existingRecipe, RecipeUpdateDto updateDto) {
        //Can be refactored by using reflection API
        if (Objects.nonNull(updateDto.getVegetarian())) {
            existingRecipe.setVegetarian(updateDto.getVegetarian());
        }
        if (Objects.nonNull(updateDto.getInstructions())) {
            existingRecipe.setInstructions(updateDto.getInstructions());
        }
        if (Objects.nonNull(updateDto.getName())) {
            existingRecipe.setName(updateDto.getName());
        }
        if (Objects.nonNull(updateDto.getNumberOfServings())) {
            existingRecipe.setNumberOfServings(updateDto.getNumberOfServings());
        }
        if (Objects.nonNull(updateDto.getIngredients())) {
            existingRecipe.setIngredients(updateDto.getIngredients().stream()
                    .map(recipeIngredientCreateDto -> RecipeIngredientMapper.INSTANCE.toEntity(recipeIngredientCreateDto, existingRecipe))
                    .collect(Collectors.toSet()));
        }
    }
}
