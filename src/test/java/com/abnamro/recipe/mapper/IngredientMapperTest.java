package com.abnamro.recipe.mapper;

import com.abnamro.recipe.TestUtils;
import com.abnamro.recipe.api.IngredientCreateDto;
import com.abnamro.recipe.api.IngredientDto;
import com.abnamro.recipe.api.IngredientMapper;
import com.abnamro.recipe.api.IngredientUpdateDto;
import com.abnamro.recipe.model.Ingredient;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class IngredientMapperTest {
    private final IngredientMapper mapper = IngredientMapper.INSTANCE;

    @Test
    void testEntityToDto() {
        Ingredient ingredient = TestUtils.createIngredient(1);
        IngredientDto ingredientDto = mapper.toDto(ingredient);
        assertThat(ingredient)
                .usingRecursiveComparison()
                .isEqualTo(ingredientDto);
    }

    @Test
    void testCreateDtoToEntity() {
        IngredientCreateDto ingredientCreateDto = new IngredientCreateDto();
        ingredientCreateDto.setName("new_ingredient");
        Ingredient ingredient = IngredientMapper.INSTANCE.toEntity(ingredientCreateDto);
        assertThat(ingredient.getName()).isEqualTo(ingredientCreateDto.getName());
    }

    @Test
    void testUpdateDtoToEntity() {
        Ingredient ingredient = TestUtils.createIngredient(1);
        IngredientUpdateDto ingredientUpdateDto = new IngredientUpdateDto();
        ingredientUpdateDto.setName("updated_name");
        IngredientMapper.INSTANCE.toUpdatedEntity(ingredient, ingredientUpdateDto);
        assertThat(ingredient.getName()).isEqualTo(ingredientUpdateDto.getName());
    }
}