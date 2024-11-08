package crm.oilwhere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import crm.oilwhere.model.Customer;

/**
 * Repository interface for managing customer entities in the database.
 * Extends JpaRepository to provide basic CRUD operations and database interaction for Customer entities.
 * 
 * <p>Commonly used methods from JpaRepository include:</p>
 * <ul>
 *     <li>{@code findAll()} - Returns all customer records in the database.</li>
 *     <li>{@code findById(id)} - Retrieves a customer record by its unique ID.</li>
 *     <li>{@code save(entity)} - Saves the specified customer entity to the database.</li>
 *     <li>{@code existsById(id)} - Checks if a customer record with the given ID exists.</li>
 *     <li>{@code deleteById(id)} - Deletes the customer record with the specified ID.</li>
 * </ul>
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
}
