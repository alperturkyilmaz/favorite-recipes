package com.abnamro.recipe.api;


import com.abnamro.recipe.core.IngredientRepository;
import com.abnamro.recipe.model.Ingredient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    @Transactional(readOnly = true)
    public IngredientDto getIngredient(Long id) {
        return IngredientMapper.INSTANCE.toDto(ingredientRepository.findById(id).orElseThrow());
    }

    @Transactional
    public IngredientDto createIngredient(IngredientCreateDto ingredientCreateDto) {
        Ingredient ingredient = IngredientMapper.INSTANCE.toEntity(ingredientCreateDto);
        ingredientRepository.save(ingredient);
        return IngredientMapper.INSTANCE.toDto(ingredient);
    }

    @Transactional
    public void deleteIngredient(Long id) {
        ingredientRepository.deleteById(id);
    }

    @Transactional
    public IngredientDto updateIngredient(Long id, IngredientUpdateDto ingredientUpdateDto) {
        Ingredient existingIngredient = ingredientRepository.findById(id).orElseThrow();
        IngredientMapper.INSTANCE.toUpdatedEntity(existingIngredient, ingredientUpdateDto);
        ingredientRepository.save(existingIngredient);
        return IngredientMapper.INSTANCE.toDto(existingIngredient);
    }
}
