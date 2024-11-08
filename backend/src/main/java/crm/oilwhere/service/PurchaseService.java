package crm.oilwhere.service;

import org.springframework.stereotype.Service;
import crm.oilwhere.dto.PurchaseDTO;
import crm.oilwhere.model.Purchase;
import crm.oilwhere.repository.PurchaseRepository;
import java.util.List;
import java.time.LocalDate;
import java.util.Optional;

/**
 * Service layer for managing purchase history data and interacting with the database via the PurchaseRepository.
 * Provides business logic for CRUD operations on the purchase history table.
 */
@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    /**
     * Constructs a new PurchaseService with the specified PurchaseRepository.
     *
     * @param purchaseRepository the repository used to interact with purchase history data in the database
     */
    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    /**
     * Retrieves all purchase records from the purchase history.
     * Uses JPA repository's {@code findAll()} method.
     *
     * @return a list of all Purchase records
     */
    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    /**
     * Retrieves purchase records within a specified date range.
     * Uses the custom query method {@code findBySaleDateBetween()} from the JPA repository.
     *
     * @param startDate the start date of the range, in LocalDate format
     * @param endDate the end date of the range, in LocalDate format
     * @return a list of Purchase records with sale dates between the specified start and end dates
     */
    public List<Purchase> getPurchaseByDateRange(LocalDate startDate, LocalDate endDate) {
        return purchaseRepository.findBySaleDateBetween(startDate, endDate);
    }

    /**
     * Creates a new purchase record in the database based on the provided PurchaseDTO.
     * Uses JPA repository's {@code save()} method.
     *
     * @param purchaseDTO the data transfer object containing details of the new purchase
     * @return the created Purchase record
     */
    public Purchase createPurchase(PurchaseDTO purchaseDTO) {
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
        return purchaseRepository.save(newPurchase);
    }

    /**
     * Deletes a purchase record from the database by its ID.
     * Uses JPA repository's {@code existsById()} and {@code deleteById()} methods.
     *
     * @param purchaseId the ID of the purchase record to delete
     * @return a confirmation message if the deletion is successful
     * @throws RuntimeException if the purchase record with the specified ID is not found
     */
    public String deletePurchase(Long purchaseId) {
        if (purchaseRepository.existsById(purchaseId)) {
            purchaseRepository.deleteById(purchaseId);
            return "Purchase record successfully deleted";
        } else {
            throw new RuntimeException("Purchase not found");
        }
    }

    /**
     * Updates an existing purchase record with new details from the provided PurchaseDTO.
     * Uses JPA repository's {@code findById()} and {@code save()} methods.
     *
     * @param purchaseId the ID of the purchase record to update
     * @param purchaseDTO the data transfer object containing updated purchase details
     * @return the updated Purchase record
     * @throws RuntimeException if the purchase record with the specified ID is not found
     */
    public Purchase updatePurchase(Long purchaseId, PurchaseDTO purchaseDTO) {
        Optional<Purchase> optionalPurchase = purchaseRepository.findById(purchaseId);

        if (optionalPurchase.isPresent()) {
            Purchase existingPurchase = optionalPurchase.get();
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
            return purchaseRepository.save(existingPurchase);
        } else {
            throw new RuntimeException("Purchase not found");
        }
    }
}
