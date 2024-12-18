package crm.oilwhere.model;

import java.util.List;

// Filters customers based on monetary medium segment
public class MediumValueCustomerFilter extends CustomerSegmentFilter {
    
    @Override
    public List<CustomerSpending> applyFilter(List<CustomerSpending> customers) {
        int bottomBoundary = (int) Math.ceil(customers.size() * 0.2);
        int upperBoundary = (int) Math.ceil(customers.size() * 0.9);
        return customers.subList(bottomBoundary, upperBoundary);
    }
}   
