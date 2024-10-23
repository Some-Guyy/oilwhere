package crm.oilwhere.util;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.springframework.stereotype.Component;

@Component
public class EmailBuilderUtil {

    public Email buildHtmlEmail(String fromName, String fromAddr, String toName, String toAddr, String subject, String htmlBody) {
        return EmailBuilder.startingBlank()
                .from(fromName, fromAddr)
                .to(toName, toAddr)
                .withSubject(subject)
                .withHTMLText(htmlBody)
                .buildEmail();
    }

    // Additional methods for HTML emails, attachments, etc.
}
