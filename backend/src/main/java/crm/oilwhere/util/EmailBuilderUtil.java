package crm.oilwhere.util;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.springframework.stereotype.Component;

@Component
public class EmailBuilderUtil {
    // Use simplejavamail's EmailBuilder class to construct an Email object
    // fromName: Name of sender (sender will be us)
    // fromAddr: Email address of sender
    // toName: Name of recipient
    // toAddr: Email address of recipient
    // subject: Email subject
    // htmlBody: Email body using html
    public Email buildHtmlEmail(String fromName, String fromAddr, String toName, String toAddr, String subject, String htmlBody) {
        return EmailBuilder.startingBlank()
                .from(fromName, fromAddr)
                .to(toName, toAddr)
                .withSubject(subject)
                .withHTMLText(htmlBody)
                .buildEmail();
    }
}
