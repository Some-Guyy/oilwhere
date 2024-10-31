package crm.oilwhere.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crm.oilwhere.dto.SendEmailToSegmentDTO;
import crm.oilwhere.model.Customer;
import crm.oilwhere.model.CustomerDTO;
import crm.oilwhere.model.Filter;
import crm.oilwhere.service.CustomerService;
import crm.oilwhere.service.FilterService;
import crm.oilwhere.service.NewsletterService;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {
    
    private final FilterService filterService;
    private final CustomerService customerService;
    private final NewsletterService newsletterService;


    public CustomerController(FilterService filterService, CustomerService customerService, NewsletterService newsletterService) {
        this.filterService = filterService;
        this.customerService = customerService;
        this.newsletterService = newsletterService;
    }

    // Send email to relevant segment
    @PostMapping("/send-email")
    public ResponseEntity<List<Customer>> testRoute(@RequestBody SendEmailToSegmentDTO sendEmailToSegmentDTO) {
        
        String segment = sendEmailToSegmentDTO.getSegment();
        List<Filter> filter = new ArrayList<>();
        List<Long> customerIds = new ArrayList<>();
        List<Customer> customerList = new ArrayList<>();
        
        // depending on segment in request body call relevant filter
        switch (segment) {
            case "high":
                filter = filterService.getHighValueCustomer();
                break;

            case "medium":
                filter = filterService.getMediumValueCustomer();
                break;

            case "low":
                filter = filterService.getLowValueCustomer();
                break;

            default:
                break;
        }

        // iterator to iterate through filter returned body
        // body consist of customerId and total spending
        Iterator<Filter> segmentIter = filter.iterator();

        // add customer ids to arraylist
        while (segmentIter.hasNext()) {
            customerIds.add(segmentIter.next().getCustomerId());           
        }

        // iterator to iterate through customer ids to get each email and name
        Iterator<Long> customerIdIter = customerIds.iterator();
        // add customer ids to arraylist
        while (customerIdIter.hasNext()) {
            Long customerId = customerIdIter.next();  
            try{
                Customer customer = customerService.getCustomerById(customerId); 
                customerList.add(customer);
            }catch(Exception e){
                e.printStackTrace();
            }
                    
        }


        return ResponseEntity.ok(customerList);
        
    }

    // get all
    @GetMapping("/get-all")
    public ResponseEntity<List<Customer>> getAllCustomer() {
        List<Customer> customer = customerService.getAllCustomer();
        return ResponseEntity.ok(customer);
    }

    // get customer by id
    @GetMapping("/get/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        return ResponseEntity.ok(customer);
    }

    // create new customer
    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer createdCustomer = customerService.createCustomer(customerDTO);
        return ResponseEntity.ok(createdCustomer);
    }

    // delete customer by id
    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long customerId) {
        String result = customerService.deleteCustomer(customerId);
        return ResponseEntity.ok(result);
    }

    // update customer
    @PutMapping("/update/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDTO customerDTO) {
        Customer customer = customerService.updateCustomer(customerId, customerDTO);
        return ResponseEntity.ok(customer);
    }
}
