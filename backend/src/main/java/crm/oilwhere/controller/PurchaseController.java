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
import java.time.LocalDate;


@RestController
@RequestMapping("/api/purchase")
@CrossOrigin(origins = "http://localhost:3000")
public class PurchaseController {
    
    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    // get all purchase history
    @GetMapping("/get-all")
    public ResponseEntity<List<Purchase>> getAllPurchases() {
        List<Purchase> purchases = purchaseService.getAllPurchases();
        return ResponseEntity.ok(purchases);
    }

    // first filter layer: get all purchase history by date range
    @GetMapping("/{startDate}/{endDate}")
    public ResponseEntity<List<Purchase>> getPurchaseByDateRange(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate) {
        List<Purchase> purchases = purchaseService.getPurchaseByDateRange(startDate, endDate);
        return ResponseEntity.ok(purchases);
    }

    // create purchase history
    @PostMapping("/create")
    public ResponseEntity<Purchase> createPurchase(@RequestBody PurchaseDTO purchaseDTO) {
        Purchase createdPurchase = purchaseService.createPurchase(purchaseDTO);
        return ResponseEntity.ok(createdPurchase);
    }

    // delete purchase history
    @DeleteMapping("/delete/{purchaseId}")
    public ResponseEntity<String> deletePurchase(@PathVariable Long purchaseId) {
        String result = purchaseService.deletePurchase(purchaseId);
        return ResponseEntity.ok(result);
    }

    // update purchase history
    @PutMapping("/update/{purchaseId}")
    public ResponseEntity<Purchase> updatePurchase(@PathVariable Long purchaseId, @RequestBody PurchaseDTO purchaseDTO) {
        Purchase purchase = purchaseService.updatePurchase(purchaseId, purchaseDTO);
        return ResponseEntity.ok(purchase);
    }
}
