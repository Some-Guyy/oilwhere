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
