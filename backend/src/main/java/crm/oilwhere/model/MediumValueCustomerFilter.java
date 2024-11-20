package crm.oilwhere.model;

import java.util.List;

/**
 * Filters customers based on their monetary value, selecting the middle 60% of customers.
 * <p>
 * This class extends {@link CustomerSegmentFilter} and provides an implementation of the
 * {@code applyFilter} method that selects customers whose total spending falls in the 
 * middle range. It assumes that the input list is pre-sorted in ascending order of spending.
 * </p>
 */
public class MediumValueCustomerFilter extends CustomerSegmentFilter {

    /**
     * Filters the middle 60% of customers based on their total spending.
     * <p>
     * The method calculates the boundaries for the bottom 20% and the top 20% of the customer list
     * and returns a sublist containing the customers in between these boundaries. The input list
     * should be pre-sorted in ascending order of total spending.
     * </p>
     *
     * @param customers the list of {@link CustomerSpending} objects, pre-sorted in ascending order by total spending
     * @return a list of {@link CustomerSpending} objects representing the middle 60% of customers
     */
    @Override
    public List<CustomerSpending> applyFilter(List<CustomerSpending> customers) {
        int bottomBoundary = (int) Math.ceil(customers.size() * 0.2);
        int upperBoundary = (int) Math.ceil(customers.size() * 0.8);
        return customers.subList(bottomBoundary, upperBoundary);
    }
}
