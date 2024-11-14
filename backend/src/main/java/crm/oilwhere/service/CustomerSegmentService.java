package crm.oilwhere.service;

import java.util.List;

import org.springframework.stereotype.Service;

import crm.oilwhere.model.CustomerSpending;
import crm.oilwhere.model.HighValueCustomerFilter;
import crm.oilwhere.model.MediumValueCustomerFilter;
import crm.oilwhere.model.LowValueCustomerFilter;
import crm.oilwhere.repository.FilterRepository;

// FilterService is the service layer connected to the FilterRepository. It contains all the business logic for customer_spending_ranked table. It utilises the JPA repository to query from the database.
// A Filter record consist of customer id and their total spending
// consists of the following functions
// getAllCustomerSpending() -- retrieve all Filter records
// getHighValueCustomer() -- retrieve customers whose total spending is at top 10% 
// getMediumValueCustomer() --  retrieve customers whose total spending is between top 10% and bottom 20%
// getLowValueCustomer() -- retrieve customers whose total spending is at bottom 20% 

@Service
public class CustomerSegmentService {

    private final FilterRepository filterRepository;
    private final HighValueCustomerFilter highValue;
    private final MediumValueCustomerFilter mediumValue;
    private final LowValueCustomerFilter lowValue;

    // constructor to create FilterService object
    public CustomerSegmentService(FilterRepository filterRepository) {
        this.filterRepository = filterRepository;
        this.highValue = new HighValueCustomerFilter();
        this.mediumValue = new MediumValueCustomerFilter();
        this.lowValue = new LowValueCustomerFilter();
    }

    // get all purchase from purchase history
    // Uses JPA repository findAll function to retrieve all Filter records
    public List<CustomerSpending> getAllCustomerSpending() {
        return filterRepository.findAll();
    }

    // get high-value customer(top 10%)
    // Uses custome query method from JPA repository, findAllByOrderByTotalSpendingDesc, to retrieve all customer records that are top 10% in spending
    public List<CustomerSpending> getHighValueCustomer() {

        // get list of all customers sorted by total spending descending
        List<CustomerSpending> customers = filterRepository.findAllByOrderByTotalSpendingDesc();

        // return sublist of top 10% only
        List<CustomerSpending> topTen = highValue.applyFilter(customers);

        return topTen;
    }
    
    // get medium-value customer(between 10% and 80%)
    // Uses custome query method from JPA repository, findAllByOrderByTotalSpendingDesc, to retrieve all customer records that are between top 10% and bottom 20% in spending
    public List<CustomerSpending> getMediumValueCustomer() {

        // get list of all customers sorted by total spending ascending
        List<CustomerSpending> customers = filterRepository.findAllByOrderByTotalSpending();

        // return sublist of between 20-80% only
        List<CustomerSpending> averageSpending = mediumValue.applyFilter(customers);

        return averageSpending;
    }

    // get low-value customer(bottom 20%)
    // Uses custome query method from JPA repository, findAllByOrderByTotalSpendingDesc, to retrieve all customer records that are bottom 20% in spending
    public List<CustomerSpending> getLowValueCustomer() {

        // get list of all customers sorted by total spending ascending
        List<CustomerSpending> customers = filterRepository.findAllByOrderByTotalSpending();

        // return sublist of bottom 20% only
        List<CustomerSpending> bottomTwenty = lowValue.applyFilter(customers);

        return bottomTwenty;
    }
}
