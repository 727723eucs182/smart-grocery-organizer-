package smartproject.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import smartproject.project.entities.Recipe;
import smartproject.project.repositories.RecipeRepository;

import java.util.List;
// import java.util.Optional;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public Recipe addRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public List<Recipe> addAllRecipes(List<Recipe> recipes) {
        return recipeRepository.saveAll(recipes);
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public List<Recipe> getRecipeByName(String recipeName) {
        return recipeRepository.findByRecipeNameContainingIgnoreCase(recipeName);
    }

    public Recipe updateRecipeByName(String recipeName, Recipe updatedRecipe) {
        List<Recipe> recipes = recipeRepository.findByRecipeNameContainingIgnoreCase(recipeName);
        if (!recipes.isEmpty()) {
            Recipe recipe = recipes.get(0);
            recipe.setIngredients(updatedRecipe.getIngredients());
            recipe.setInstructions(updatedRecipe.getInstructions());
            return recipeRepository.save(recipe);
        }
        return null;
    }

    
    public boolean deleteRecipeById(int id) {
        if (recipeRepository.existsById(id)) {
            recipeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Page<Recipe> getRecipesPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return recipeRepository.findAll(pageable);
    }

    public List<Recipe> getRecipesSorted(String field) {
        return recipeRepository.findAll(Sort.by(field));
    }

    public Page<Recipe> getRecipesPaginatedSorted(int page, int size, String field) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(field));
        return recipeRepository.findAll(pageable);
    }
}
