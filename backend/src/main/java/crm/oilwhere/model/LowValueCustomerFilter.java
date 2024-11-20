package crm.oilwhere.model;

import java.util.List;

/**
 * Filters customers based on their monetary value, selecting the bottom 20% of customers.
 * <p>
 * This class extends {@link CustomerSegmentFilter} and provides an implementation of the
 * {@code applyFilter} method that selects the lowest spending customers. It assumes that
 * the input list is pre-sorted in ascending order of spending.
 * </p>
 */
public class LowValueCustomerFilter extends CustomerSegmentFilter {

    /**
     * Filters the bottom 20% of customers based on their total spending.
     * <p>
     * The method calculates the bottom 20% of the customer list and returns a sublist containing
     * these customers. The input list should be pre-sorted in ascending order of total spending.
     * </p>
     *
     * @param customers the list of {@link CustomerSpending} objects, pre-sorted in ascending order by total spending
     * @return a list of {@link CustomerSpending} objects representing the bottom 20% of customers
     */
    @Override
    public List<CustomerSpending> applyFilter(List<CustomerSpending> customers) {
        int bottomTwentyLength = (int) Math.ceil(customers.size() * 0.2);
        return customers.subList(0, bottomTwentyLength);
    }
}
