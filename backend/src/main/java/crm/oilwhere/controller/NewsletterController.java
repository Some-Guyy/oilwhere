package crm.oilwhere.controller;

import crm.oilwhere.dto.EmailDTO;
import crm.oilwhere.dto.NewsletterDTO;
import crm.oilwhere.dto.UserDTO;
import crm.oilwhere.model.Newsletter;
import crm.oilwhere.model.User;
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
    @GetMapping("/get-all")
    public ResponseEntity<List<Newsletter>> getById() {
        List<Newsletter> newsletters = NewsletterService.getAll();
        return ResponseEntity.ok(newsletters);
    }
    
    // update template
    @PutMapping("/{id}")
    public ResponseEntity<Newsletter> updateTemplate(@PathVariable Long id, @RequestBody NewsletterDTO newsletterDTO) {
        Newsletter newsletter = NewsletterService.updateTemplate(id, newsletterDTO);
        return ResponseEntity.ok(newsletter);
    }
    
    //create new template
    @PostMapping("/create")
    public ResponseEntity<Newsletter> createNewsletter(@RequestBody NewsletterDTO newsletterDTO) {
        Newsletter newsletter = NewsletterService.createNewsletter(newsletterDTO);
        return ResponseEntity.ok(newsletter);
    }

    //delete email
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTemplate(@PathVariable Long id) {
        String result = NewsletterService.deleteTemplate(id);
        return ResponseEntity.ok(result);
    }

    // send a single email
    @PostMapping("/email")
    public ResponseEntity<EmailDTO> testRoute(@RequestBody EmailDTO emailDTO) {
        NewsletterService.sendMail(emailDTO.getName(), emailDTO.getEmail(), emailDTO.getSubject(), emailDTO.getBody());
        return ResponseEntity.ok(emailDTO);
    }
}
