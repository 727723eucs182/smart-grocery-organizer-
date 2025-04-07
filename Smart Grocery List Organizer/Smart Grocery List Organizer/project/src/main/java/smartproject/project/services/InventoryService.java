package smartproject.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import smartproject.project.entities.Inventory;
import smartproject.project.repositories.InventoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public Inventory addInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public List<Inventory> addAllInventory(List<Inventory> inventories) {
        return inventoryRepository.saveAll(inventories);
    }

    public Inventory getInventoryById(int id) {
        Optional<Inventory> inventory = inventoryRepository.findById(id);
        return inventory.orElse(null);
    }

    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    public List<Inventory> getInventoryByItemName(String itemName) {
        return inventoryRepository.findByItemName(itemName);
    }

    public Inventory updateInventory(int id, Inventory updatedInventory) {
        Optional<Inventory> existingInventory = inventoryRepository.findById(id);
        if (existingInventory.isPresent()) {
            Inventory inventory = existingInventory.get();
            inventory.setItemName(updatedInventory.getItemName());
            inventory.setQuantity(updatedInventory.getQuantity());
            inventory.setUnit(updatedInventory.getUnit());
            return inventoryRepository.save(inventory);
        }
        return null;
    }

    public boolean updateQuantityByItemName(String itemName, int quantity) {
        List<Inventory> inventoryList = inventoryRepository.findByItemName(itemName);
        if (!inventoryList.isEmpty()) {
            for (Inventory inventory : inventoryList) {
                inventory.setQuantity(quantity);
                inventoryRepository.save(inventory);
            }
            return true;
        }
        return false;
    }

    public boolean deleteInventory(int id) {
        if (inventoryRepository.existsById(id)) {
            inventoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean deleteInventoryByUnit(String unit) {
        List<Inventory> inventoryList = inventoryRepository.findByUnit(unit);
        if (!inventoryList.isEmpty()) {
            inventoryRepository.deleteAll(inventoryList);
            return true;
        }
        return false;
    }

    public Page<Inventory> getInventoryPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return inventoryRepository.findAll(pageable);
    }

    public List<Inventory> getSortedInventory(String field) {
        return inventoryRepository.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    public Page<Inventory> getPaginatedAndSortedInventory(int page, int size, String field) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, field));
        return inventoryRepository.findAll(pageable);
    }
}
