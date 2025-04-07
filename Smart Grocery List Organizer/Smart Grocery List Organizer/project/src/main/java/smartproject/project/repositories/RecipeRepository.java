package smartproject.project.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;
import smartproject.project.entities.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    // ✅ Find Recipes by Partial Name (Case-Insensitive)
    List<Recipe> findByRecipeNameContainingIgnoreCase(String recipeName);

    // ✅ Update Recipe by Name
    @Modifying
    @Transactional
    @Query("UPDATE Recipe r SET r.ingredients = :ingredients, r.instructions = :instructions WHERE r.recipeName = :recipeName")
    int updateRecipeByName(String recipeName, String ingredients, String instructions);

    // ✅ Delete Recipe by Name
    @Modifying
    @Transactional
    @Query("DELETE FROM Recipe r WHERE r.recipeName = :recipeName")
    int deleteByRecipeName(String recipeName);
}
