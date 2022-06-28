package com.abnamro.recipe.api;

import com.abnamro.recipe.model.QRecipe;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.util.Optional;


@RequiredArgsConstructor
public class RecipeQuerydslBinderCustomizer implements QuerydslBinderCustomizer<QRecipe> {

    @Override
    public void customize(QuerydslBindings bindings, QRecipe root) {
        bindings.bind(root.id).withDefaultBinding();
        bindings.bind(root.name).all((path, value) -> {
            BooleanBuilder builder = new BooleanBuilder();
            value.forEach(s -> builder.or(path.containsIgnoreCase(s)));
            return Optional.ofNullable(builder);
        });
        bindings.bind(root.vegetarian).withDefaultBinding();
        bindings.bind(root.numberOfServings).withDefaultBinding();
        bindings.bind(root.instructions).first((path, value) -> path.containsIgnoreCase(value));
        bindings.bind(root.ingredients.any().ingredient.name).as("include").all((path, value) -> {
            BooleanBuilder builder = new BooleanBuilder();
            value.forEach(s -> builder.and(path.equalsIgnoreCase(s)));
            return Optional.ofNullable(builder);
        });
    }
}
