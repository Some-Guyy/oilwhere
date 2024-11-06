package crm.oilwhere.service;

import java.util.List;

import org.springframework.stereotype.Service;

import crm.oilwhere.model.Filter;
import crm.oilwhere.repository.FilterRepository;


@Service
public class FilterService {

    private final FilterRepository filterRepository;

    // constructor
    public FilterService(FilterRepository filterRepository) {
        this.filterRepository = filterRepository;
    }

    // get all purchase from purchase history
    public List<Filter> getAllCustomerSpending() {
        return filterRepository.findAll();
    }

    // get high-value customer(top 10%)
    public List<Filter> getHighValueCustomer() {

        // get list of all customers sorted by total spending descending
        List<Filter> customers = filterRepository.findAllByOrderByTotalSpendingDesc();

        // find the length of top 10%
        int topTenLength = (int)Math.ceil(customers.size() * 0.1);

        // return sublist of top 10% only
        return customers.subList(0, topTenLength);
    }
    
    // get medium-value customer(between 10% and 80%)
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
    public List<Filter> getLowValueCustomer() {

        // get list of all customers sorted by total spending ascending
        List<Filter> customers = filterRepository.findAllByOrderByTotalSpending();

        // find the length of bottom 20%
        int bottomTwentyLength = (int)Math.ceil(customers.size() * 0.2);

        // return sublist of bottom 20% only
        return customers.subList(0, bottomTwentyLength);
    }
}
