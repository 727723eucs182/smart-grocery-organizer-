package smartproject.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smartproject.project.entities.Recipe;
import smartproject.project.services.RecipeService;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @PostMapping
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {
        return new ResponseEntity<>(recipeService.addRecipe(recipe), HttpStatus.CREATED);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Recipe>> addAllRecipes(@RequestBody List<Recipe> recipes) {
        return new ResponseEntity<>(recipeService.addAllRecipes(recipes), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        return new ResponseEntity<>(recipeService.getAllRecipes(), HttpStatus.OK);
    }

    @GetMapping("/search/{recipeName}")
    public ResponseEntity<List<Recipe>> getRecipeByName(@PathVariable String recipeName) {
        List<Recipe> recipes = recipeService.getRecipeByName(recipeName);
        return recipes.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @PutMapping("/update/{recipeName}")
    public ResponseEntity<Recipe> updateRecipeByName(@PathVariable String recipeName, @RequestBody Recipe updatedRecipe) {
        Recipe recipe = recipeService.updateRecipeByName(recipeName, updatedRecipe);
        return recipe != null ? new ResponseEntity<>(recipe, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipeById(@PathVariable int id) {
        return recipeService.deleteRecipeById(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/page/{page}/{size}")
    public ResponseEntity<Page<Recipe>> getRecipesPaginated(@PathVariable int page, @PathVariable int size) {
        return new ResponseEntity<>(recipeService.getRecipesPaginated(page, size), HttpStatus.OK);
    }

    @GetMapping("/sort/{field}")
    public ResponseEntity<List<Recipe>> getRecipesSorted(@PathVariable String field) {
        return new ResponseEntity<>(recipeService.getRecipesSorted(field), HttpStatus.OK);
    }

    @GetMapping("/page/{page}/{size}/sort/{field}")
    public ResponseEntity<Page<Recipe>> getRecipesPaginatedSorted(
            @PathVariable int page, @PathVariable int size, @PathVariable String field) {
        return new ResponseEntity<>(recipeService.getRecipesPaginatedSorted(page, size, field), HttpStatus.OK);
    }
}
