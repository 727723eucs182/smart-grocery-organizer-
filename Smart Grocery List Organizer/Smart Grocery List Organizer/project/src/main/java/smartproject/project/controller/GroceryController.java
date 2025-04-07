package smartproject.project.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import smartproject.project.entities.Grocery;
import smartproject.project.services.GroceryService;

@RestController
@RequestMapping("/groceries") 
public class GroceryController {

    private final GroceryService groceryService;

    public GroceryController(GroceryService groceryService) {
        this.groceryService = groceryService;
    }

  
    @PostMapping("/post")
    public ResponseEntity<Grocery> addGrocery(@RequestBody Grocery grocery) {
        Grocery savedGrocery = groceryService.addGrocery(grocery);
        return new ResponseEntity<>(savedGrocery, HttpStatus.CREATED);
    }

  
    @PostMapping("/postList")
    public ResponseEntity<List<Grocery>> addAllGrocery(@RequestBody List<Grocery> groceries) {
        List<Grocery> savedGroceries = groceryService.addAllGrocery(groceries);
        return new ResponseEntity<>(savedGroceries, HttpStatus.CREATED);
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Grocery>> getGroceryByName(@PathVariable String name) {
        List<Grocery> groceries = groceryService.getGroceryByName(name);
        return groceries.isEmpty()
            ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
            : new ResponseEntity<>(groceries, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Grocery>> getAllGroceries() {
        List<Grocery> groceries = groceryService.getAllGroceries();
        return new ResponseEntity<>(groceries, HttpStatus.OK);
    }


    @PutMapping("/{name}")
    public ResponseEntity<Grocery> updateGrocery(@PathVariable String name, @RequestBody Grocery updatedGrocery) {
        Grocery grocery = groceryService.editGrocery(name, updatedGrocery.getId(), updatedGrocery.getPrice());
        return grocery != null
            ? new ResponseEntity<>(grocery, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteGrocery(@PathVariable String name) {
        boolean isDeleted = groceryService.deleteGrocery(name);
        return isDeleted
            ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/page/{offset}/{pageSize}")
    public ResponseEntity<List<Grocery>> getGroceriesPaginated(@PathVariable int offset, @PathVariable int pageSize) {
        List<Grocery> paginatedGroceries = groceryService.getGroceriesPaginated(offset, pageSize);
        return new ResponseEntity<>(paginatedGroceries, HttpStatus.OK);
    }

    @GetMapping("/sort/{field}")
    public ResponseEntity<List<Grocery>> sortGroceries(@PathVariable String field) {
        List<Grocery> sortedGroceries = groceryService.sortGroceries(field);
        return new ResponseEntity<>(sortedGroceries, HttpStatus.OK);
    }

    @GetMapping("/page/{offset}/{pageSize}/sort/{field}")
    public ResponseEntity<List<Grocery>> getGroceriesPaginatedSorted(
            @PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {
        List<Grocery> sortedPaginatedGroceries = groceryService.getGroceriesPaginatedSorted(offset, pageSize, field);
        return new ResponseEntity<>(sortedPaginatedGroceries, HttpStatus.OK);
    }
}
