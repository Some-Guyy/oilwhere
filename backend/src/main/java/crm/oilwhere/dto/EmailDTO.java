package crm.oilwhere.dto;

/**
 * Data Transfer Object for email details.
 * Contains the segment, subject, and body of an email.
 */
public class EmailDTO {

    private String segment;
    private String subject;
    private String body;

    /**
     * Retrieves the customer segment to which the email is targeted.
     *
     * @return the email segment
     */
    public String getSegment() {
        return segment;
    }

    /**
     * Sets the customer segment to which the email is targeted.
     *
     * @param segment the segment to set for the email
     */
    public void setSegment(String segment) {
        this.segment = segment;
    }

    /**
     * Retrieves the subject of the email.
     *
     * @return the email subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the subject of the email.
     *
     * @param subject the subject to set for the email
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Retrieves the body content of the email.
     *
     * @return the email body
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets the body content of the email.
     *
     * @param body the body content to set for the email
     */
    public void setBody(String body) {
        this.body = body;
    }
}
