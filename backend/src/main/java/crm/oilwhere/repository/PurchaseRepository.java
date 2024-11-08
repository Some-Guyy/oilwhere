package crm.oilwhere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import crm.oilwhere.model.Purchase;
import java.util.List;
import java.time.LocalDate;

/**
 * Repository interface for managing purchase records in the database.
 * Extends JpaRepository to provide basic CRUD operations and custom query methods for Purchase entities.
 * 
 * <p>Commonly used methods from JpaRepository include:</p>
 * <ul>
 *     <li>{@code findAll()} - Retrieves all purchase records in the database.</li>
 *     <li>{@code findById(id)} - Retrieves a purchase record by its unique ID.</li>
 *     <li>{@code save(entity)} - Saves the specified purchase entity to the database.</li>
 *     <li>{@code existsById(id)} - Checks if a purchase record with the given ID exists.</li>
 *     <li>{@code deleteById(id)} - Deletes the purchase record with the specified ID.</li>
 * </ul>
 * 
 * <p>Custom query methods:</p>
 * <ul>
 *     <li>{@code findBySaleDateBetween(startDate, endDate)} - Retrieves a list of Purchase records
 *         where the sale date falls between the specified start and end dates.</li>
 * </ul>
 */
@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    /**
     * Retrieves a list of Purchase records where the sale date falls between the specified start and end dates.
     *
     * @param startDate the start date of the date range, in LocalDate format (e.g., 2019-11-23)
     * @param endDate the end date of the date range, in LocalDate format (e.g., 2019-11-23)
     * @return a list of Purchase records within the specified date range
     */
    List<Purchase> findBySaleDateBetween(LocalDate startDate, LocalDate endDate);
}
