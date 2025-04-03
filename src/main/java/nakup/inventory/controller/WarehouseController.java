package nakup.inventory.controller;

import nakup.inventory.dto.WarehouseAddRequest;
import nakup.inventory.dto.WarehouseResponse;
import nakup.inventory.model.Warehouse;
import nakup.inventory.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/inventory/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @PostMapping
    public String addWarehouse(@RequestBody WarehouseAddRequest request) {
        warehouseService.addWarehouse(request);
        return "success, added: " + request.getName() + ", " + request.getAddress();
    }

    @GetMapping
    public List<WarehouseResponse> getAllWarehouses() {
        return warehouseService.getAllWarehouses();
    }
}
