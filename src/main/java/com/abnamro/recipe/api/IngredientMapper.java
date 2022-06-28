package com.abnamro.recipe.api;

import com.abnamro.recipe.model.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IngredientMapper {
    IngredientMapper INSTANCE = Mappers.getMapper(IngredientMapper.class);

    IngredientDto toDto(Ingredient item);

    Ingredient toEntity(IngredientCreateDto item);

    default void toUpdatedEntity(Ingredient existingIngredient, IngredientUpdateDto updateDto) {
        //Can be refactored by using reflection
        existingIngredient.setName(updateDto.getName());

    }

}
