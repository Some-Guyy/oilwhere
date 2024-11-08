package crm.oilwhere.dto;

/**
 * Data Transfer Object for newsletter template details.
 * Contains the design ID, name, and content of a newsletter template.
 */
public class NewsletterDTO {
    private Long designId;
    private String name;
    private String content;

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
}
