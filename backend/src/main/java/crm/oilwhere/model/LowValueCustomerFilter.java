package crm.oilwhere.model;

import java.util.List;

public class LowValueCustomerFilter extends CustomerSegmentFilter {
    
    @Override
    public List<CustomerSpending> applyFilter(List<CustomerSpending> customers) {
        int bottomTwentyLength = (int) Math.ceil(customers.size() * 0.2);
        return customers.subList(0, bottomTwentyLength);
    }
}   
