package crm.oilwhere.dto;

/**
 * Data Transfer Object for newsletter template details.
 * Contains the design ID, name, content, and user ID of a newsletter template.
 */
public class NewsletterDTO {

    /** The unique identifier for the design of the newsletter template. */
    private Long designId;

    /** The name of the newsletter template. */
    private String name;

    /** The content of the newsletter template. */
    private String content;

    /** The unique identifier of the user associated with the newsletter template. */
    private Long userId;

    /**
     * Retrieves the unique design ID of the newsletter template.
     *
     * @return the design ID
     */
    public Long getId() {
        return designId;
    }

    /**
     * Sets the unique design ID of the newsletter template.
     *
     * @param designId the design ID to set
     */
    public void setId(Long designId) {
        this.designId = designId;
    }

    /**
     * Retrieves the name of the newsletter template.
     *
     * @return the name of the template
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the newsletter template.
     *
     * @param name the name to set for the template
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the content of the newsletter template.
     *
     * @return the content of the template
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the newsletter template.
     *
     * @param content the content to set for the template
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Retrieves the unique identifier of the user associated with the newsletter template.
     *
     * @return the user ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets the unique identifier of the user associated with the newsletter template.
     *
     * @param userId the user ID to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
