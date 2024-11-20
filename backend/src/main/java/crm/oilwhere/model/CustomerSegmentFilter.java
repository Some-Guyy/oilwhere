package crm.oilwhere.model;

import java.util.List;

// Filters customers based on segment
public abstract class CustomerSegmentFilter {
    public abstract List<CustomerSpending> applyFilter(List<CustomerSpending> customers);
}
