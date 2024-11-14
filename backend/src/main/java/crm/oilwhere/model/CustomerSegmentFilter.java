package crm.oilwhere.model;

import java.util.List;

public abstract class CustomerSegmentFilter {
    public abstract List<CustomerSpending> applyFilter(List<CustomerSpending> customers);
}
