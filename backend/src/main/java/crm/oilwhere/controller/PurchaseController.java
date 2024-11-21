package crm.oilwhere.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crm.oilwhere.service.PurchaseService;
import crm.oilwhere.dto.PurchaseDTO;
import crm.oilwhere.model.Purchase;

import java.util.List;

/**
 * Controller for managing purchase records in the purchase history table.
 * Provides endpoints for CRUD operations on purchase records.
 */
@RestController
@RequestMapping("/api/purchase")
@CrossOrigin(origins = "http://localhost:3000")
public class PurchaseController {
    
    private final PurchaseService purchaseService;

    /**
     * Constructs a new PurchaseController with the specified PurchaseService.
     *
     * @param purchaseService the service used to manage purchase operations
     */
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    /**
     * Retrieves all purchase records from the purchase history.
     *
     * @return a ResponseEntity containing a list of all purchase records
     */
    @GetMapping("/get-all")
    public ResponseEntity<List<Purchase>> getAllPurchases() {
        List<Purchase> purchases = purchaseService.getAllPurchases();
        return ResponseEntity.ok(purchases);
    }

    /**
     * Creates a new purchase record and adds it to the purchase history.
     *
     * @param purchaseDTO the data transfer object containing purchase details
     * @return a ResponseEntity containing the created Purchase object
     */
    @PostMapping("/create")
    public ResponseEntity<Purchase> createPurchase(@RequestBody PurchaseDTO purchaseDTO) {
        Purchase createdPurchase = purchaseService.createPurchase(purchaseDTO);
        return ResponseEntity.ok(createdPurchase);
    }

    /**
     * Deletes a purchase record by its ID.
     *
     * @param purchaseId the ID of the purchase record to delete
     * @return a ResponseEntity containing a message indicating the result of the deletion
     */
    @DeleteMapping("/delete/{purchaseId}")
    public ResponseEntity<String> deletePurchase(@PathVariable Long purchaseId) {
        String result = purchaseService.deletePurchase(purchaseId);
        return ResponseEntity.ok(result);
    }

    /**
     * Updates an existing purchase record by its ID with the details provided in the PurchaseDTO.
     *
     * @param purchaseId the ID of the purchase record to update
     * @param purchaseDTO the data transfer object containing the updated purchase details
     * @return a ResponseEntity containing the updated Purchase object
     */
    @PutMapping("/update/{purchaseId}")
    public ResponseEntity<Purchase> updatePurchase(@PathVariable Long purchaseId, @RequestBody PurchaseDTO purchaseDTO) {
        Purchase purchase = purchaseService.updatePurchase(purchaseId, purchaseDTO);
        return ResponseEntity.ok(purchase);
    }
}
