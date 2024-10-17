package crm.oilwhere.service;

import org.springframework.stereotype.Service;

import crm.oilwhere.dto.PurchaseDTO;
import crm.oilwhere.model.Purchase;
import crm.oilwhere.repository.PurchaseRepository;

import java.util.List;
import java.time.LocalDate;

import java.util.Optional;

@Service
public class PurchaseService {
    
    private final PurchaseRepository purchaseRepository;

    // constructor
    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    // get all purchase from purchase history
    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    // get all purchase within start date and end date from purchase history
    public List<Purchase> getPurchaseByDateRange(LocalDate startDate, LocalDate endDate) {
        return purchaseRepository.findBySaleDateBetween(startDate, endDate);
    }

    // create new purchase record
    public Purchase createPurchase(PurchaseDTO purchaseDTO) {
    
        // Create a new Purchase instance
        Purchase newPurchase = new Purchase();
        
        newPurchase.setSaleDate(purchaseDTO.getSaleDate());
        newPurchase.setSaleType(purchaseDTO.getSaleType());
        newPurchase.setDigital(purchaseDTO.getDigital());
        newPurchase.setCustomerId(purchaseDTO.getCustomerId());
        newPurchase.setZipcode(purchaseDTO.getZipcode());
        newPurchase.setShippingMethod(purchaseDTO.getShippingMethod());
        newPurchase.setProduct(purchaseDTO.getProduct());
        newPurchase.setVariant(purchaseDTO.getVariant());
        newPurchase.setQuantity(purchaseDTO.getQuantity());
        newPurchase.setPrice(purchaseDTO.getPrice());
        newPurchase.setProductPrice(purchaseDTO.getProductPrice());
    
        // Save the new Purchase to the database
        return purchaseRepository.save(newPurchase);
    }

    // Delete purchase record by id
    public String deletePurchase(Long purchaseId) {
        if (purchaseRepository.existsById(purchaseId)) {
            purchaseRepository.deleteById(purchaseId);
            return "Purchase record successfully deleted";
        } else {
            throw new RuntimeException("Purchase not found");
        }
    }

    // Update purchase record
    public Purchase updatePurchase(Long purchaseId, PurchaseDTO purchaseDTO) {
    Optional<Purchase> optionalPurchase = purchaseRepository.findById(purchaseId);

    if (optionalPurchase.isPresent()) {
        Purchase existingPurchase = optionalPurchase.get();

        // Update any relevant field
        existingPurchase.setSaleDate(purchaseDTO.getSaleDate());
        existingPurchase.setSaleType(purchaseDTO.getSaleType());
        existingPurchase.setDigital(purchaseDTO.getDigital());
        existingPurchase.setCustomerId(purchaseDTO.getCustomerId());
        existingPurchase.setZipcode(purchaseDTO.getZipcode());
        existingPurchase.setShippingMethod(purchaseDTO.getShippingMethod());
        existingPurchase.setProduct(purchaseDTO.getProduct());
        existingPurchase.setVariant(purchaseDTO.getVariant());
        existingPurchase.setQuantity(purchaseDTO.getQuantity());
        existingPurchase.setPrice(purchaseDTO.getPrice());
        existingPurchase.setProductPrice(purchaseDTO.getProductPrice());

        // Save the updated user
        return purchaseRepository.save(existingPurchase);
    } else {
        throw new RuntimeException("User not found");
    }
}

    
}
