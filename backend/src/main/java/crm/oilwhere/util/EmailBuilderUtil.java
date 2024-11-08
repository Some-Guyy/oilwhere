package crm.oilwhere.util;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.springframework.stereotype.Component;

/**
 * Utility class for building HTML email messages.
 * Provides methods for constructing email messages with specified sender, recipient, subject, and HTML body content.
 */
@Component
public class EmailBuilderUtil {

    /**
     * Builds an HTML email with the specified sender and recipient details, subject, and HTML body content.
     *
     * @param fromName the name of the sender
     * @param fromAddr the email address of the sender
     * @param toName the name of the recipient
     * @param toAddr the email address of the recipient
     * @param subject the subject of the email
     * @param htmlBody the HTML content of the email body
     * @return an Email object containing the constructed HTML email
     */
    public Email buildHtmlEmail(String fromName, String fromAddr, String toName, String toAddr, String subject, String htmlBody) {
        return EmailBuilder.startingBlank()
                .from(fromName, fromAddr)
                .to(toName, toAddr)
                .withSubject(subject)
                .withHTMLText(htmlBody)
                .buildEmail();
    }

    // Additional methods for building emails with attachments, images, etc., can be added here.
}
