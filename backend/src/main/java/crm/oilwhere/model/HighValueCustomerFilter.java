package crm.oilwhere.model;

import java.util.List;

// Filters customers based on monetary high segment
public class HighValueCustomerFilter extends CustomerSegmentFilter {
    
    @Override
    public List<CustomerSpending> applyFilter(List<CustomerSpending> customers) {
        int topTenLength = (int)Math.ceil(customers.size() * 0.1);
        return customers.subList(0, topTenLength);
    }
}   
