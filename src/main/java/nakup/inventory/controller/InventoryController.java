package nakup.inventory.controller;

import nakup.inventory.dto.InventoryCreateRequest;
import nakup.inventory.model.Inventory;
import nakup.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/inventory")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @PostMapping("/create")
    public String createInventory(@RequestBody InventoryCreateRequest request) {
        inventoryService.save(request);
        return "successssssssssssss";
    }
}
