package crm.oilwhere.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import crm.oilwhere.model.CustomerSpending;

// The following is the repository layer which interacts with the database
// It helps the FilterService access the JPA repository and utilise functions provided by it. These include
// .findAllByOrderByTotalSpendingDesc() -- This custom query retrieves all Filter records from the database, ordered by the totalSpending attribute in descending order (highest to lowest).
// .findAllByOrderByTotalSpending() -- This custom query retrieves all Filter records from the database, ordered by the totalSpending attribute in ascending order (lowest to highest).

@Repository
public interface FilterRepository extends JpaRepository<CustomerSpending, Long>{
    // Returns a list of all Filter records, sorted from the highest to the lowest value of totalSpending
    List<CustomerSpending> findAllByOrderByTotalSpendingDesc();

    // Returns a list of all Filter records, sorted from the lowest to the highest value of totalSpending
    List<CustomerSpending> findAllByOrderByTotalSpending();

}
