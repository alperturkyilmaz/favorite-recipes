package com.abnamro.recipe.core;

import com.abnamro.recipe.model.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long> {

}