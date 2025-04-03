package nakup.inventory.controller;

import nakup.inventory.dto.WarehouseAddRequest;
import nakup.inventory.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
