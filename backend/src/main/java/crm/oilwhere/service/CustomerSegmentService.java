package crm.oilwhere.service;

import java.util.List;
import org.springframework.stereotype.Service;
import crm.oilwhere.model.CustomerSpending;
import crm.oilwhere.repository.FilterRepository;

/**
 * Service layer for managing customer spending data from the customer_spending_ranked table.
 * Provides business logic to categorize customers by their spending levels.
 * Utilizes the FilterRepository to perform database queries.
 */
@Service
public class CustomerSegmentService {

    private final FilterRepository filterRepository;

    /**
     * Constructs a new FilterService with the specified FilterRepository.
     *
     * @param filterRepository the repository used to interact with the customer spending data in the database
     */
    public CustomerSegmentService(FilterRepository filterRepository) {
        this.filterRepository = filterRepository;
    }

    /**
     * Retrieves all customer spending records from the database.
     * Uses JPA repository's {@code findAll()} method.
     *
     * @return a list of all Filter records representing customer spending
     */
    public List<CustomerSpending> getAllCustomerSpending() {
        return filterRepository.findAll();
    }

    /**
     * Retrieves customers whose total spending is in the top 10%.
     * Uses a custom query method to retrieve all customer records ordered by total spending in descending order,
     * then returns only the top 10%.
     *
     * @return a list of CustomerSpending records representing the top 10% of customers by spending
     */
    public List<CustomerSpending> getHighValueCustomer() {
        List<CustomerSpending> customers = filterRepository.findAllByOrderByTotalSpendingDesc();
        int topTenLength = (int) Math.ceil(customers.size() * 0.1);
        return customers.subList(0, topTenLength);
    }
    
    /**
     * Retrieves customers whose total spending falls between the top 10% and bottom 20%.
     * Uses a custom query method to retrieve all customer records ordered by total spending in ascending order,
     * then returns only those between the 10% and 80% range.
     *
     * @return a list of CustomerSpending records representing customers with spending between 10% and 80%
     */
    public List<CustomerSpending> getMediumValueCustomer() {
        List<CustomerSpending> customers = filterRepository.findAllByOrderByTotalSpending();
        int bottomBoundary = (int) Math.ceil(customers.size() * 0.2);
        int upperBoundary = (int) Math.ceil(customers.size() * 0.8);
        return customers.subList(bottomBoundary, upperBoundary);
    }

    /**
     * Retrieves customers whose total spending is in the bottom 20%.
     * Uses a custom query method to retrieve all customer records ordered by total spending in ascending order,
     * then returns only the bottom 20%.
     *
     * @return a list of CustomerSpending records representing the bottom 20% of customers by spending
     */
    public List<CustomerSpending> getLowValueCustomer() {
        List<CustomerSpending> customers = filterRepository.findAllByOrderByTotalSpending();
        int bottomTwentyLength = (int) Math.ceil(customers.size() * 0.2);
        return customers.subList(0, bottomTwentyLength);
    }
}
