package crm.oilwhere.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crm.oilwhere.model.Filter;
import crm.oilwhere.service.FilterService;

// The following is the endpoints used to access the customer_spending_ranked table
// It includes the following results
// /get-all -- Obtain all customer records and their respective spending in the customer_spending_ranked table.
// /get/highValue -- Obtain top 10% of customers based on spending in the customer_spending_ranked table 
// /get/mediumValue -- Obtain between 10% and 80% of customers based on spending in the customer_spending_ranked table
// /get/lowValue -- Obtain bottom 20% of customers based on spending in the customer_spending_ranked table

@RestController
@RequestMapping("/api/filter")
@CrossOrigin(origins = "http://localhost:3000")
public class FilterController {
    
    private final FilterService filterService;

    public FilterController(FilterService filterService) {
        this.filterService = filterService;
    }

    // get all customer and their spending
    // GET request
    // Returns list of Filter objects
    @GetMapping("/get-all")
    public ResponseEntity<List<Filter>> getAllCustomerSpending() {
        List<Filter> filter = filterService.getAllCustomerSpending();
        return ResponseEntity.ok(filter);
    }

    // get high-value customer(top 10%)
    // GET request
    // Returns list of Filter objects that have totalSpending at top 10%
    @GetMapping("/get/highValue")
    public ResponseEntity<List<Filter>> getHighValueCustomer() {
        List<Filter> filter = filterService.getHighValueCustomer();
        return ResponseEntity.ok(filter);
    }

    // get medium-value customer(between 10% and 80%)
    // GET request
    // Returns list of Filter objects that have totalSpending between 10% and 80%
    @GetMapping("/get/mediumValue")
    public ResponseEntity<List<Filter>> getMediumValueCustomer() {
        List<Filter> filter = filterService.getMediumValueCustomer();
        return ResponseEntity.ok(filter);
    }

    // get low-value customer(bottom 20%)
    // GET request
    // Returns list of Filter objects that have totalSpending at bottom 20%
    @GetMapping("/get/lowValue")
    public ResponseEntity<List<Filter>> getLowValueCustomer() {
        List<Filter> filter = filterService.getLowValueCustomer();
        return ResponseEntity.ok(filter);
    }

}
