package crm.oilwhere.model;

import java.util.List;

/**
 * Filters customers based on their monetary value, selecting the top 10% of customers.
 * <p>
 * This class extends {@link CustomerSegmentFilter} and provides an implementation of the
 * {@code applyFilter} method that selects the highest spending customers. It assumes that
 * the input list is pre-sorted in descending order of spending.
 * </p>
 */
public class HighValueCustomerFilter extends CustomerSegmentFilter {

    /**
     * Filters the top 10% of customers based on their total spending.
     * <p>
     * The method calculates the top 10% of the customer list and returns a sublist containing
     * these customers. The input list should be pre-sorted in descending order of total spending.
     * </p>
     *
     * @param customers the list of {@link CustomerSpending} objects, pre-sorted in descending order by total spending
     * @return a list of {@link CustomerSpending} objects representing the top 10% of customers
     */
    @Override
    public List<CustomerSpending> applyFilter(List<CustomerSpending> customers) {
        int topTenLength = (int) Math.ceil(customers.size() * 0.1);
        return customers.subList(0, topTenLength);
    }
}
