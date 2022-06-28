package com.abnamro.recipe.api;


import com.abnamro.recipe.core.RecipeRepository;
import com.abnamro.recipe.model.Recipe;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;


    @Transactional(readOnly = true)
    public List<RecipeDto> getRecipes(Predicate predicate) {
        return RecipeMapper.INSTANCE.toDto(recipeRepository.findAll(predicate));
    }

    @Transactional(readOnly = true)
    public RecipeDto getRecipe(Long id) {
        return RecipeMapper.INSTANCE.toDto(recipeRepository.findById(id).orElseThrow());
    }

    @Transactional
    public RecipeDto createRecipe(@Valid RecipeCreateDto recipeCreateDto) {
        Recipe recipe = RecipeMapper.INSTANCE.toEntity(recipeCreateDto);
        recipeRepository.save(recipe);

        recipe.setIngredients(recipeCreateDto.getIngredients().stream()
                .map(recipeIngredientCreateDto -> RecipeIngredientMapper.INSTANCE.toEntity(recipeIngredientCreateDto, recipe))
                .collect(Collectors.toSet()));
        recipeRepository.save(recipe);
        return RecipeMapper.INSTANCE.toDto(recipe);
    }

    @Transactional
    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }

    @Transactional
    public RecipeDto updateRecipe(Long id, RecipeUpdateDto recipeUpdateDto) {
        Recipe existingRecipe = recipeRepository.findById(id).orElseThrow();
        RecipeMapper.INSTANCE.toUpdatedEntity(existingRecipe, recipeUpdateDto);
        recipeRepository.save(existingRecipe);
        return RecipeMapper.INSTANCE.toDto(existingRecipe);

    }


}
