package crm.oilwhere.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import crm.oilwhere.model.CustomerSpending;

/**
 * Repository interface for managing filter entities in the database.
 * Extends JpaRepository to provide basic CRUD operations and custom query methods for Filter entities.
 * 
 * <p>Commonly used custom methods:</p>
 * <ul>
 *     <li>{@code findAllByOrderByTotalSpendingDesc()} - Retrieves all Filter records from the database, 
 *         ordered by the totalSpending attribute in descending order (highest to lowest).</li>
 *     <li>{@code findAllByOrderByTotalSpending()} - Retrieves all Filter records from the database, 
 *         ordered by the totalSpending attribute in ascending order (lowest to highest).</li>
 * </ul>
 */
@Repository
public interface FilterRepository extends JpaRepository<CustomerSpending, Long> {

    /**
     * Retrieves a list of all Filter records, sorted by total spending in descending order.
     *
     * @return a list of Filter records ordered from highest to lowest totalSpending
     */
    List<CustomerSpending> findAllByOrderByTotalSpendingDesc();

    /**
     * Retrieves a list of all Filter records, sorted by total spending in ascending order.
     *
     * @return a list of Filter records ordered from lowest to highest totalSpending
     */
    List<CustomerSpending> findAllByOrderByTotalSpending();

}
