package crm.oilwhere.service;

import crm.oilwhere.dto.NewsletterDTO;
import crm.oilwhere.model.Newsletter;
import crm.oilwhere.repository.NewsletterRepository;
import crm.oilwhere.util.EmailBuilderUtil;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Service layer for managing newsletter templates and sending emails.
 * Provides business logic for CRUD operations on the Newsletter table and email sending functionality.
 */
@Service
public class NewsletterService {

    private final NewsletterRepository newsletterRepository;
    private final Mailer mailer;
    private final EmailBuilderUtil emailBuilderUtil;

    /**
     * Constructs a new NewsletterService with the specified repository, mailer, and email builder utility.
     *
     * @param newsletterRepository the repository used to interact with the newsletter data in the database
     * @param mailer the mailer used to send emails
     * @param emailBuilderUtil the utility used to build email messages
     */
    @Autowired
    public NewsletterService(NewsletterRepository newsletterRepository, Mailer mailer, EmailBuilderUtil emailBuilderUtil) {
        this.newsletterRepository = newsletterRepository;
        this.mailer = mailer;
        this.emailBuilderUtil = emailBuilderUtil;
    }

    /**
     * Retrieves all newsletter templates from the database.
     * Uses JPA repository's {@code findAll()} method.
     *
     * @return a list of all Newsletter records
     */
    public List<Newsletter> getAll() {
        return newsletterRepository.findAll();
    }

    /**
     * Updates an existing newsletter template with new details from the provided NewsletterDTO.
     * Uses JPA repository's {@code findById()} and {@code save()} methods.
     *
     * @param id the ID of the newsletter to update
     * @param newsletterDTO the data transfer object containing updated newsletter details
     * @return the updated Newsletter record
     * @throws RuntimeException if the newsletter with the specified ID is not found
     */
    public Newsletter updateTemplate(Long id, NewsletterDTO newsletterDTO) {
        Optional<Newsletter> optionalNewsletter = newsletterRepository.findById(id);

        if (optionalNewsletter.isPresent()) {
            Newsletter existingTemplate = optionalNewsletter.get();
            existingTemplate.setName(newsletterDTO.getName());
            existingTemplate.setContent(newsletterDTO.getContent());
            return newsletterRepository.save(existingTemplate);
        } else {
            throw new RuntimeException("Newsletter not found");
        }
    }

    /**
     * Creates a new newsletter template in the database based on the provided NewsletterDTO.
     * Uses JPA repository's {@code save()} method.
     *
     * @param newsletterDTO the data transfer object containing details of the new newsletter template
     * @return the created Newsletter record
     */
    public Newsletter createNewsletter(NewsletterDTO newsletterDTO) {
        Newsletter newTemplate = new Newsletter();
        newTemplate.setName(newsletterDTO.getName());
        newTemplate.setContent(newsletterDTO.getContent());
        return newsletterRepository.save(newTemplate);
    }

    /**
     * Deletes a newsletter template from the database by its ID.
     * Uses JPA repository's {@code existsById()} and {@code deleteById()} methods.
     *
     * @param id the ID of the newsletter template to delete
     * @return a confirmation message if the deletion is successful
     * @throws RuntimeException if the newsletter with the specified ID is not found
     */
    public String deleteTemplate(Long id) {
        if (newsletterRepository.existsById(id)) {
            newsletterRepository.deleteById(id);
            return "Template deleted";
        } else {
            throw new RuntimeException("Template not found");
        }
    }

    /**
     * Sends an email to a customer with the specified details.
     * Constructs an HTML email message using the customer's name, email, subject, and body, then sends it using the configured mailer.
     *
     * @param custName the name of the customer receiving the email
     * @param custEmail the email address of the customer
     * @param subject the subject of the email
     * @param body the HTML body content of the email
     */
    public void sendMail(String custName, String custEmail, String subject, String body) {
        try {
            String fromName = "Timperio";
            String fromAddr = System.getenv("SMTP_USERNAME");
            Email email = this.emailBuilderUtil.buildHtmlEmail(fromName, fromAddr, custName, custEmail, subject, body);
            this.mailer.sendMail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
