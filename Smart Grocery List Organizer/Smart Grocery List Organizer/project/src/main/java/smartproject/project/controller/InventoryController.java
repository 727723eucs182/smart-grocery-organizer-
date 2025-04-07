package smartproject.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smartproject.project.entities.Inventory;
import smartproject.project.services.InventoryService;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/post")
    public ResponseEntity<Inventory> addInventory(@RequestBody Inventory inventory) {
        return new ResponseEntity<>(inventoryService.addInventory(inventory), HttpStatus.CREATED);
    }

    @PostMapping("/postList")
    public ResponseEntity<List<Inventory>> addAllInventory(@RequestBody List<Inventory> inventories) {
        return new ResponseEntity<>(inventoryService.addAllInventory(inventories), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable int id) {
        Inventory inventory = inventoryService.getInventoryById(id);
        return inventory != null ? new ResponseEntity<>(inventory, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Inventory>> getAllInventory() {
        return new ResponseEntity<>(inventoryService.getAllInventory(), HttpStatus.OK);
    }

    @GetMapping("/name/{itemName}")
    public ResponseEntity<List<Inventory>> getInventoryByItemName(@PathVariable String itemName) {
        List<Inventory> inventoryList = inventoryService.getInventoryByItemName(itemName);
        return inventoryList.isEmpty()
            ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
            : new ResponseEntity<>(inventoryList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable int id, @RequestBody Inventory updatedInventory) {
        Inventory inventory = inventoryService.updateInventory(id, updatedInventory);
        return inventory != null ? new ResponseEntity<>(inventory, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{itemName}")
    public ResponseEntity<String> updateQuantityByItemName(@PathVariable String itemName, @RequestParam int quantity) {
        boolean updated = inventoryService.updateQuantityByItemName(itemName, quantity);
        return updated
            ? new ResponseEntity<>("Inventory quantity updated successfully", HttpStatus.OK)
            : new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable int id) {
        return inventoryService.deleteInventory(id)
            ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteByUnit/{unit}")
    public ResponseEntity<String> deleteInventoryByUnit(@PathVariable String unit) {
        boolean deleted = inventoryService.deleteInventoryByUnit(unit);
        return deleted
            ? new ResponseEntity<>("Inventory items deleted successfully", HttpStatus.OK)
            : new ResponseEntity<>("No items found for the given unit", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/page/{page}/{size}")
    public ResponseEntity<Page<Inventory>> getInventoryPaginated(@PathVariable int page, @PathVariable int size) {
        return new ResponseEntity<>(inventoryService.getInventoryPaginated(page, size), HttpStatus.OK);
    }

    @GetMapping("/sort/{field}")
    public ResponseEntity<List<Inventory>> getSortedInventory(@PathVariable String field) {
        return new ResponseEntity<>(inventoryService.getSortedInventory(field), HttpStatus.OK);
    }

    @GetMapping("/page/{page}/{size}/sort/{field}")
    public ResponseEntity<Page<Inventory>> getPaginatedAndSortedInventory(
            @PathVariable int page, @PathVariable int size, @PathVariable String field) {
        return new ResponseEntity<>(inventoryService.getPaginatedAndSortedInventory(page, size, field), HttpStatus.OK);
    }
}
