package smartproject.project.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import smartproject.project.entities.Grocery;
import smartproject.project.repositories.GroceryRepository;

import java.util.List;

@Service
public class GroceryService {
    
    private final GroceryRepository groceryRepository;

    public GroceryService(GroceryRepository groceryRepository) {
        this.groceryRepository = groceryRepository;
    }

    // Add a single grocery
    public Grocery addGrocery(Grocery grocery) {
        return groceryRepository.save(grocery);
    }

    // Add multiple groceries
    public List<Grocery> addAllGrocery(List<Grocery> groceries) {
        return groceryRepository.saveAll(groceries);
    }

    // Retrieve grocery by name
    public List<Grocery> getGroceryByName(String name) {
        return groceryRepository.findByName(name);
    }

    // Retrieve all groceries
    public List<Grocery> getAllGroceries() {
        return groceryRepository.findAll();
    }

    // Update a grocery by name
    public Grocery editGrocery(String name, int id, Double price) {
        List<Grocery> groceries = groceryRepository.findByName(name);
        if (!groceries.isEmpty()) {
            Grocery grocery = groceries.get(0);
            grocery.setId(0);
            grocery.setPrice(price);
            return groceryRepository.save(grocery);
        }
        return null;
    }

    // Delete grocery by name
    public boolean deleteGrocery(String name) {
        List<Grocery> groceries = groceryRepository.findByName(name);
        if (!groceries.isEmpty()) {
            groceryRepository.deleteAll(groceries);
            return true;
        }
        return false;
    }

    // Pagination
    public List<Grocery> getGroceriesPaginated(int offset, int pageSize) {
        Pageable pageable = PageRequest.of(offset, pageSize);
        Page<Grocery> groceryPage = groceryRepository.findAll(pageable);
        return groceryPage.getContent();
    }

    // Sorting
    public List<Grocery> sortGroceries(String field) {
        return groceryRepository.findAll(Sort.by(field));
    }

    // Pagination + Sorting
    public List<Grocery> getGroceriesPaginatedSorted(int offset, int pageSize, String field) {
        Pageable pageable = PageRequest.of(offset, pageSize, Sort.by(field));
        Page<Grocery> groceryPage = groceryRepository.findAll(pageable);
        return groceryPage.getContent();
    }
}
