package crm.oilwhere.model;

import java.util.List;

/**
 * Abstract class for filtering customers based on specific segments.
 * <p>
 * This class serves as a blueprint for implementing various customer filtering
 * strategies based on segments. Subclasses should provide their own implementation
 * of the {@code applyFilter} method to define the filtering logic.
 * </p>
 */
public abstract class CustomerSegmentFilter {

    /**
     * Applies a filter to a list of customers and returns the filtered list.
     *
     * @param customers the list of {@link CustomerSpending} objects to be filtered
     * @return a list of {@link CustomerSpending} objects that meet the filter criteria
     */
    public abstract List<CustomerSpending> applyFilter(List<CustomerSpending> customers);
}
