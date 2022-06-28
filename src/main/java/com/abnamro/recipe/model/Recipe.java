package com.abnamro.recipe.model;


import lombok.*;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "recipe")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false, unique = true)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 256)
    private String name;

    @Builder.Default
    private Boolean vegetarian = Boolean.FALSE;

    @NotNull
    @Min(1)
    private Integer numberOfServings;

    @NotBlank
    private String instructions;

    @OneToMany(
            mappedBy = "recipe",
            cascade = CascadeType.MERGE,
            orphanRemoval = true
    )
    @Builder.Default
    private Set<RecipeIngredient> ingredients = new HashSet<>();

    public void setIngredients(Set<RecipeIngredient> ingredients) {
        this.ingredients.clear();
        if (!CollectionUtils.isEmpty(ingredients)) {
            this.ingredients.addAll(ingredients);
        }
    }
}
