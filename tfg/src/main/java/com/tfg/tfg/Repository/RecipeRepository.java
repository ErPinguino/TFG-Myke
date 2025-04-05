package com.tfg.tfg.Repository;

import com.tfg.tfg.Entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
