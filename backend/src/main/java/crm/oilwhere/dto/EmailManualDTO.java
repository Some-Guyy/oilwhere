package crm.oilwhere.dto;

/**
 * A Data Transfer Object (DTO) that represents the details required for sending an email manually.
 * This includes the recipients' emails, the subject of the email, and the body content.
 */
public class EmailManualDTO {

    /** The email addresses of the recipients, typically separated by commas. */
    private String emails;

    /** The subject line of the email. */
    private String subject;

    /** The body content of the email. */
    private String body;

    /**
     * Gets the email addresses of the recipients.
     *
     * @return a {@code String} containing the email addresses, typically separated by commas.
     */
    public String getEmails() {
        return emails;
    }

    /**
     * Sets the email addresses of the recipients.
     *
     * @param emails a {@code String} containing the email addresses, typically separated by commas.
     */
    public void setEmails(String emails) {
        this.emails = emails;
    }

    /**
     * Gets the subject of the email.
     *
     * @return a {@code String} containing the subject of the email.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the subject of the email.
     *
     * @param subject a {@code String} containing the subject of the email.
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Gets the body content of the email.
     *
     * @return a {@code String} containing the body content of the email.
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets the body content of the email.
     *
     * @param body a {@code String} containing the body content of the email.
     */
    public void setBody(String body) {
        this.body = body;
    }
}
