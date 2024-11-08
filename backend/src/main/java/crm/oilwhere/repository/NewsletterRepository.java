package crm.oilwhere.repository;

import crm.oilwhere.model.Newsletter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing newsletter entities in the database.
 * Extends JpaRepository to provide basic CRUD operations for Newsletter entities.
 * 
 * <p>Commonly used methods from JpaRepository include:</p>
 * <ul>
 *     <li>{@code findAll()} - Retrieves all newsletter records in the database.</li>
 *     <li>{@code findById(id)} - Retrieves a newsletter record by its unique ID.</li>
 *     <li>{@code save(entity)} - Saves the specified newsletter entity to the database.</li>
 *     <li>{@code existsById(id)} - Checks if a newsletter record with the given ID exists.</li>
 *     <li>{@code deleteById(id)} - Deletes the newsletter record with the specified ID.</li>
 * </ul>
 */
@Repository
public interface NewsletterRepository extends JpaRepository<Newsletter, Long> {

}
