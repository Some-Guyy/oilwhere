package crm.oilwhere.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crm.oilwhere.model.Filter;
import crm.oilwhere.service.FilterService;

@RestController
@RequestMapping("/api/filter")
@CrossOrigin(origins = "http://localhost:3000")
public class FilterController {
    
    private final FilterService filterService;

    public FilterController(FilterService filterService) {
        this.filterService = filterService;
    }

    // get all customer and their spending
    @GetMapping("/get-all")
    public ResponseEntity<List<Filter>> getAllCustomerSpending() {
        List<Filter> filter = filterService.getAllCustomerSpending();
        return ResponseEntity.ok(filter);
    }

    // get high-value customer(top 10%)
    @GetMapping("/get/highValue")
    public ResponseEntity<List<Filter>> getHighValueCustomer() {
        List<Filter> filter = filterService.getHighValueCustomer();
        return ResponseEntity.ok(filter);
    }

    // get medium-value customer(between 10% and 80%)
    @GetMapping("/get/mediumValue")
    public ResponseEntity<List<Filter>> getMediumValueCustomer() {
        List<Filter> filter = filterService.getMediumValueCustomer();
        return ResponseEntity.ok(filter);
    }

    // get low-value customer(bottom 20%)
    @GetMapping("/get/lowValue")
    public ResponseEntity<List<Filter>> getLowValueCustomer() {
        List<Filter> filter = filterService.getLowValueCustomer();
        return ResponseEntity.ok(filter);
    }

}
