package crm.oilwhere.service;

import java.util.List;

import org.springframework.stereotype.Service;

import crm.oilwhere.model.Filter;
import crm.oilwhere.repository.FilterRepository;

// FilterService is the service layer connected to the FilterRepository. It contains all the business logic for customer_spending_ranked table. It utilises the JPA repository to query from the database.
// A Filter record consist of customer id and their total spending
// consists of the following functions
// getAllCustomerSpending() -- retrieve all Filter records
// getHighValueCustomer() -- retrieve customers whose total spending is at top 10% 
// getMediumValueCustomer() --  retrieve customers whose total spending is between top 10% and bottom 20%
// getLowValueCustomer() -- retrieve customers whose total spending is at bottom 20% 

@Service
public class FilterService {

    private final FilterRepository filterRepository;

    // constructor to create FilterService object
    public FilterService(FilterRepository filterRepository) {
        this.filterRepository = filterRepository;
    }

    // get all purchase from purchase history
    // Uses JPA repository findAll function to retrieve all Filter records
    public List<Filter> getAllCustomerSpending() {
        return filterRepository.findAll();
    }

    // get high-value customer(top 10%)
    // Uses custome query method from JPA repository, findAllByOrderByTotalSpendingDesc, to retrieve all customer records that are top 10% in spending
    public List<Filter> getHighValueCustomer() {

        // get list of all customers sorted by total spending descending
        List<Filter> customers = filterRepository.findAllByOrderByTotalSpendingDesc();

        // find the length of top 10%
        int topTenLength = (int)Math.ceil(customers.size() * 0.1);

        // return sublist of top 10% only
        return customers.subList(0, topTenLength);
    }
    
    // get medium-value customer(between 10% and 80%)
    // Uses custome query method from JPA repository, findAllByOrderByTotalSpendingDesc, to retrieve all customer records that are between top 10% and bottom 20% in spending
    public List<Filter> getMediumValueCustomer() {

        // get list of all customers sorted by total spending ascending
        List<Filter> customers = filterRepository.findAllByOrderByTotalSpending();

        // find the length of bottom 20%
        int bottomBoundary = (int)Math.ceil(customers.size() * 0.2);

        // find the length of top 80%
        int upperBoundary = (int)Math.ceil(customers.size() * 0.8);

        // return sublist of between 20-80% only
        return customers.subList(bottomBoundary, upperBoundary);
    }

    // get low-value customer(bottom 20%)
    // Uses custome query method from JPA repository, findAllByOrderByTotalSpendingDesc, to retrieve all customer records that are bottom 20% in spending
    public List<Filter> getLowValueCustomer() {

        // get list of all customers sorted by total spending ascending
        List<Filter> customers = filterRepository.findAllByOrderByTotalSpending();

        // find the length of bottom 20%
        int bottomTwentyLength = (int)Math.ceil(customers.size() * 0.2);

        // return sublist of bottom 20% only
        return customers.subList(0, bottomTwentyLength);
    }
}
