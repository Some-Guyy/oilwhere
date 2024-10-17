package crm.oilwhere.service;

import crm.oilwhere.dto.NewsletterDTO;
import crm.oilwhere.model.Newsletter;
import crm.oilwhere.repository.NewsletterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsletterService {

    private final NewsletterRepository newsletterRepository;

    // constructor
    public NewsletterService(NewsletterRepository newsletterRepository) {
        this.newsletterRepository = newsletterRepository;
    }

    // get newsletter by id
    public Newsletter getById(Long id) {
        return newsletterRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // update templates
    public Newsletter updateTemplate(Long id, NewsletterDTO newsletterDTO) {
        Optional<Newsletter> optionalNewsletter = newsletterRepository.findById(id);

        if (optionalNewsletter.isPresent()) {
            Newsletter existingTemplate = optionalNewsletter.get();
            existingTemplate.setContent(newsletterDTO.getContent());

            return newsletterRepository.save(existingTemplate);
        } else {
            throw new RuntimeException("Newsletter not found");
        }

    }

}
