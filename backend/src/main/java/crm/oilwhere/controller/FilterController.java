package crm.oilwhere.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crm.oilwhere.model.Filter;
import crm.oilwhere.service.FilterService;

/**
 * Controller for accessing customer spending records in the customer_spending_ranked table.
 * Provides endpoints to retrieve customer records based on spending categories.
 */
@RestController
@RequestMapping("/api/filter")
@CrossOrigin(origins = "http://localhost:3000")
public class FilterController {
    
    private final FilterService filterService;

    /**
     * Constructs a new FilterController with the specified FilterService.
     *
     * @param filterService the service used to manage filter operations on customer spending data
     */
    public FilterController(FilterService filterService) {
        this.filterService = filterService;
    }

    /**
     * Retrieves all customer records along with their respective spending amounts.
     *
     * @return a ResponseEntity containing a list of all Filter objects with customer spending details
     */
    @GetMapping("/get-all")
    public ResponseEntity<List<Filter>> getAllCustomerSpending() {
        List<Filter> filter = filterService.getAllCustomerSpending();
        return ResponseEntity.ok(filter);
    }

    /**
     * Retrieves the top 10% of customers based on their spending.
     *
     * @return a ResponseEntity containing a list of Filter objects representing the top 10% of customers by spending
     */
    @GetMapping("/get/highValue")
    public ResponseEntity<List<Filter>> getHighValueCustomer() {
        List<Filter> filter = filterService.getHighValueCustomer();
        return ResponseEntity.ok(filter);
    }

    /**
     * Retrieves customers who fall within the middle range of spending (between 10% and 80%).
     *
     * @return a ResponseEntity containing a list of Filter objects representing customers with spending between 10% and 80%
     */
    @GetMapping("/get/mediumValue")
    public ResponseEntity<List<Filter>> getMediumValueCustomer() {
        List<Filter> filter = filterService.getMediumValueCustomer();
        return ResponseEntity.ok(filter);
    }

    /**
     * Retrieves the bottom 20% of customers based on their spending.
     *
     * @return a ResponseEntity containing a list of Filter objects representing the bottom 20% of customers by spending
     */
    @GetMapping("/get/lowValue")
    public ResponseEntity<List<Filter>> getLowValueCustomer() {
        List<Filter> filter = filterService.getLowValueCustomer();
        return ResponseEntity.ok(filter);
    }
}
