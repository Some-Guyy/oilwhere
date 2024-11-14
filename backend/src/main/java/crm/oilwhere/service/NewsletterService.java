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

// NewsletterService is the service layer connected to the NewsletterRepository. It contains all the business logic for Newsletter table. It utilises the JPA repository to query from the database.
// consists of the following functions
// getAll() -- retrieve all Newsletter records
// updateTemplate(id, newsletterDTO) -- update the newsletter record with the specified id and replace with newsletterDTO object
// createNewsletter(newsletterDTO) -- create a new newsletter record with the newsletterDTO object
// deleteTemplate(id) -- delete the newsletter record with the specific id
// sendMail(custName, custEmail, subject, body) -- Send email to relevant customer with custEmail

@Service
public class NewsletterService {

    private final NewsletterRepository newsletterRepository;
    private final Mailer mailer;
    private final EmailBuilderUtil emailBuilderUtil;

    // constructor
    @Autowired
    public NewsletterService(NewsletterRepository newsletterRepository, Mailer mailer, EmailBuilderUtil emailBuilderUtil) {
        this.newsletterRepository = newsletterRepository;
        this.mailer = mailer;
        this.emailBuilderUtil = emailBuilderUtil;
    }

    // get all templates
    // Uses JPA repository findAll function to retrieve all Newsletter records
    public List<Newsletter> getAll() {
        return newsletterRepository.findAll();
    }

    // update templates
    // Used JPA repository's findById to find the record with specified id and isPresent to check if it exists
    // Obtain the record'snewsletter object
    // Set the name and content from the newsletterDTO object using getName, getContent on the DTO
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

    //create newsletter template
    // Create a new Newsletter object
    // Set the name and content from the newsletterDTO object using getName, getContent
    // Used JPA repository to save the record after creating the new template object
    public Newsletter createNewsletter(NewsletterDTO newsletterDTO) {

        // new template instance
        Newsletter newTemplate = new Newsletter();
        newTemplate.setName(newsletterDTO.getName());
        newTemplate.setContent(newsletterDTO.getContent());
    
        // Save the new user to the database
        return newsletterRepository.save(newTemplate);
    }

    // delete newsletter template
    // Used JPA repository's existsById to find if the record with specified customerId exists
    // Used JPA repository's deleteById to delete the record with specified customerId exists
    public String deleteTemplate(Long id) {
        if (newsletterRepository.existsById(id)) {
            newsletterRepository.deleteById(id);
            return "Template deleted";
        } else {
            throw new RuntimeException("Template not found");
        }
    }

    // Function to send email
    // Set the senders name as Timperio
    // Get the SMTP_USERNAME from the env file to set the from email address
    // Build the email using the senders name, the senders email address, the customers name, customers email with the subject and the body
    public void sendMail(String custName, String custEmail, String subject, String body) {
        try {
            String fromName = "Timperio";
            String fromAddr = System.getenv("SMTP_USERNAME");
            
            if (body.contains("<name>")) {
                body = body.replaceAll("<name>", custName);
            }

            Email email = this.emailBuilderUtil.buildHtmlEmail(fromName, fromAddr, custName, custEmail, subject, body);
            this.mailer.sendMail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
