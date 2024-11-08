package crm.oilwhere.controller;

import crm.oilwhere.dto.NewsletterDTO;
import crm.oilwhere.model.Newsletter;
import crm.oilwhere.service.NewsletterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing newsletter templates in the newsletter table.
 * Provides endpoints for CRUD operations on newsletter templates.
 */
@RestController
@RequestMapping("/api/newsletter")
@CrossOrigin(origins = "http://localhost:3000")
public class NewsletterController {

    private final NewsletterService newsletterService;

    /**
     * Constructs a new NewsletterController with the specified NewsletterService.
     *
     * @param newsletterService the service used to manage newsletter operations
     */
    public NewsletterController(NewsletterService newsletterService) {
        this.newsletterService = newsletterService;
    }

    /**
     * Retrieves all newsletter templates from the newsletter table.
     *
     * @return a ResponseEntity containing a list of all newsletter templates
     */
    @GetMapping("/get-all")
    public ResponseEntity<List<Newsletter>> getById() {
        List<Newsletter> newsletters = newsletterService.getAll();
        return ResponseEntity.ok(newsletters);
    }

    /**
     * Updates an existing newsletter template by its ID with the details provided in the NewsletterDTO.
     *
     * @param id the ID of the newsletter template to update
     * @param newsletterDTO the data transfer object containing the updated template content
     * @return a ResponseEntity containing the updated Newsletter object
     */
    @PutMapping("/{id}")
    public ResponseEntity<Newsletter> updateTemplate(@PathVariable Long id, @RequestBody NewsletterDTO newsletterDTO) {
        Newsletter newsletter = newsletterService.updateTemplate(id, newsletterDTO);
        return ResponseEntity.ok(newsletter);
    }

    /**
     * Creates a new newsletter template and adds it to the newsletter table.
     *
     * @param newsletterDTO the data transfer object containing the template details
     * @return a ResponseEntity containing the created Newsletter object
     */
    @PostMapping("/create")
    public ResponseEntity<Newsletter> createNewsletter(@RequestBody NewsletterDTO newsletterDTO) {
        Newsletter newsletter = newsletterService.createNewsletter(newsletterDTO);
        return ResponseEntity.ok(newsletter);
    }

    /**
     * Deletes a newsletter template by its ID.
     *
     * @param id the ID of the newsletter template to delete
     * @return a ResponseEntity containing a message indicating the result of the deletion
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTemplate(@PathVariable Long id) {
        String result = newsletterService.deleteTemplate(id);
        return ResponseEntity.ok(result);
    }
}
