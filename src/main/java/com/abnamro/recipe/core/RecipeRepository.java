package com.abnamro.recipe.core;

import com.abnamro.recipe.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;


public interface RecipeRepository extends JpaRepository<Recipe, Long>,
        QuerydslPredicateExecutor<Recipe> {

}