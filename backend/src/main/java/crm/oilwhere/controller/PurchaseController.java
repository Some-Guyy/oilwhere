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

// The following is the endpoints used to access the purchase history table
// It includes the following results
// /get-all -- Obtain all purchase record in the purchase history table.
// /{startDate}/{endDate} -- Obtain all purchase record in the purchase history table between the start and end date
// /create -- Create a new purchase record and put it inside the purchase history table
// /delete/{purchaseId} -- Delete specific purchase record by the purchaseId
// /update/{purchaseId} -- Update specific purchase record by the purchaseId 

@RestController
@RequestMapping("/api/purchase")
@CrossOrigin(origins = "http://localhost:3000")
public class PurchaseController {
    
    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    // get all purchase history
    // GET request
    // Returns list of Purchase objects
    @GetMapping("/get-all")
    public ResponseEntity<List<Purchase>> getAllPurchases() {
        List<Purchase> purchases = purchaseService.getAllPurchases();
        return ResponseEntity.ok(purchases);
    }

    // create purchase history
    // POST request
    // Takes in a PurchaseDTO object
    // Returns the Purchase object that was created
    @PostMapping("/create")
    public ResponseEntity<Purchase> createPurchase(@RequestBody PurchaseDTO purchaseDTO) {
        Purchase createdPurchase = purchaseService.createPurchase(purchaseDTO);
        return ResponseEntity.ok(createdPurchase);
    }

    // Delete purchase history
    // DELETE request
    // Takes in a purchaseId which is the numerical id of the purchase record
    // Returns message "Purchase record successfully deleted" if success
    // Returns error 500 if unsuccessful
    @DeleteMapping("/delete/{purchaseId}")
    public ResponseEntity<String> deletePurchase(@PathVariable Long purchaseId) {
        String result = purchaseService.deletePurchase(purchaseId);
        return ResponseEntity.ok(result);
    }

    // Update purchase history
    // PUT request
    // Takes in a purchaseId and a purchaseDTO object which overrides the record of the selected purchaseId with the PurchaseDTO object
    // Returns newly updated purchase object if successful
    // Returns error 500 if unsuccessful
    @PutMapping("/update/{purchaseId}")
    public ResponseEntity<Purchase> updatePurchase(@PathVariable Long purchaseId, @RequestBody PurchaseDTO purchaseDTO) {
        Purchase purchase = purchaseService.updatePurchase(purchaseId, purchaseDTO);
        return ResponseEntity.ok(purchase);
    }
}
