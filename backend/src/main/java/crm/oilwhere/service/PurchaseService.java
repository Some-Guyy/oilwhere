package crm.oilwhere.service;

import org.springframework.stereotype.Service;

import crm.oilwhere.dto.PurchaseDTO;
import crm.oilwhere.model.Purchase;
import crm.oilwhere.repository.PurchaseRepository;

import java.util.List;

import java.util.Optional;

// PurchaseService is the service layer connected to the PurchaseRepository. It contains all the business logic for purchase history table. It utilises the JPA repository to query from the database.
// consists of the following functions
// getAllPurchases() -- retrieve all purchase records
// getPurchaseByDateRange(startDate, endDate) -- retrieve purchase record where date falls between a specified start and end date
// createPurchase(purchaseDTO) -- create a new purchase record with the purchaseDTO object
// deletePurchase(purchaseId) -- delete the purchase record with the specidic purchaseId
// updatePurchase(purchaseId, purchaseDTO) -- update the purchase record with the specified purchaseId and replace with purchaseDTO object 

@Service
public class PurchaseService {
    
    private final PurchaseRepository purchaseRepository;

    // constructor to create PurchaseService object
    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    // get all purchase from purchase history
    // Uses JPA repository findAll function to retrieve all customer records
    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    // create new purchase record
    // Create a new purchase object
    // Set the sale date, sale type, digital, customer id, zipcode, shipping method, product, variant, quantity, price, product price from the purchaseDTO object using the following methods
    // Used JPA repository to save the record after creating the new customer object
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
    // Used JPA repository's existsById to find if the record with specified purchaseId exists
    // Used JPA repository's deleteById to delete the record with specified purchaseId exists
    public String deletePurchase(Long purchaseId) {
        if (purchaseRepository.existsById(purchaseId)) {
            purchaseRepository.deleteById(purchaseId);
            return "Purchase record successfully deleted";
        } else {
            throw new RuntimeException("Purchase not found");
        }
    }

    // Update purchase record
    // Used JPA repository's findById to find the record with specified customerId and isPresent to check if it exists
    // obtain the records Purchase object 
    // Set the sale date, sale type, digital, customer id, zipcode, shipping method, product, variant, quantity, price, product price from the purchaseDTO object using the following methods
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
