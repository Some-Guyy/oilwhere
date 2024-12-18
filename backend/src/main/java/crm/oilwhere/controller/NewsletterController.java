package crm.oilwhere.controller;

import crm.oilwhere.dto.NewsletterDTO;
import crm.oilwhere.model.Newsletter;
import crm.oilwhere.service.NewsletterService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// The following is the endpoints used to access the newsletter table
// It includes the following results
// /get-all -- Obtain all newsletter record in the newsletter table.
// /{id} -- Obtain specific newsletter record in the newsletter table with specified id
// /create -- Create a new newsletter record and put it inside the newsletter table
// /delete/{id} -- Delete specific newsletter record by the id

@RestController
@RequestMapping("/api/newsletter")
@CrossOrigin(origins = "http://localhost:3000")
public class NewsletterController {

    private final NewsletterService NewsletterService;

    public NewsletterController(NewsletterService NewsletterService) {
        this.NewsletterService = NewsletterService;
    }

    // get all newsletter template
    // GET request
    // Returns list of newletter objects
    @GetMapping("/get-all")
    public ResponseEntity<List<Newsletter>> getAll() {
        List<Newsletter> newsletters = NewsletterService.getAll();
        return ResponseEntity.ok(newsletters);
    }
    
    // update template
    // PUT request
    // Takes in a id and newsletterDTO object which overrides the record of the selected designId with the newsletterDTO object
    // Returns updated newsletter object created
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTemplate(@PathVariable Long id, @RequestBody NewsletterDTO newsletterDTO) {
        try {
            Newsletter newsletter = NewsletterService.updateTemplate(id, newsletterDTO);
            return ResponseEntity.ok(newsletter);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    // create new template
    // POST request
    // Takes in a NewsletterDTO
    // Returns the Newsletter object created
    @PostMapping("/create")
    public ResponseEntity<Newsletter> createNewsletter(@RequestBody NewsletterDTO newsletterDTO) {
        Newsletter newsletter = NewsletterService.createNewsletter(newsletterDTO);
        return ResponseEntity.ok(newsletter);
    }

    // delete email
    // DELETE request
    // Takes in a id which is the numerical id of the Newsletter record
    // Returns messgae "Template deleted" if success
    // Returns error 500 if unsuccessful
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTemplate(@PathVariable Long id) {
        String result = NewsletterService.deleteTemplate(id);
        return ResponseEntity.ok(result);
    }

}
