package crm.oilwhere.controller;

import crm.oilwhere.dto.NewsletterDTO;
import crm.oilwhere.model.Newsletter;
import crm.oilwhere.service.NewsletterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/newsletter")
@CrossOrigin(origins = "http://localhost:3000")
public class NewsletterController {

    private final NewsletterService NewsletterService;

    public NewsletterController(NewsletterService NewsletterService) {
        this.NewsletterService = NewsletterService;
    }

    // get template by id
    @GetMapping("/{id}")
    public ResponseEntity<Newsletter> getById(@PathVariable Long id) {
        Newsletter newsletter = NewsletterService.getById(id);
        return ResponseEntity.ok(newsletter);
    }
    
    //update template
    @PutMapping("/{id}")
    public ResponseEntity<Newsletter> updateTemplate(@PathVariable Long id, @RequestBody NewsletterDTO newsletterDTO) {
        Newsletter newsletter = NewsletterService.updateTemplate(id, newsletterDTO);
        return ResponseEntity.ok(newsletter);
    }

}
