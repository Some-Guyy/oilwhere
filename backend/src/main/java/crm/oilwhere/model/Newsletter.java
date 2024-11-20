package crm.oilwhere.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

/**
 * Represents a newsletter entity with details about each newsletter design.
 * Maps to the "newsletter" table in the database.
 * Contains fields for the design ID, name, and content of the newsletter.
 */
@Entity
@Table(name = "newsletter")
public class Newsletter {

    /**
     * The unique identifier for the newsletter design.
     * <p>
     * This field is the primary key and is automatically generated using the 
     * identity generation strategy. It maps to the "design_id" column in the database.
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "design_id")
    private Long designId;

    /**
     * The name of the newsletter design.
     * <p>
     * This field maps to the "name" column in the database and represents the title
     * or identifier for the design.
     * </p>
     */
    @Column(name = "name")
    private String name;

    /**
     * The content of the newsletter design.
     * <p>
     * This field stores the content of the newsletter in text format. It is a 
     * large object (LOB) and maps to the "content" column in the database with
     * a column definition of "TEXT".
     * </p>
     */
    @Lob
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;


    /**
     * Default constructor for Newsletter.
     */
    public Newsletter() {}

    /**
     * Constructs a Newsletter with the specified design ID, name, and content.
     *
     * @param designId the unique identifier of the newsletter
     * @param name the name of the newsletter
     * @param content the content of the newsletter
     */
    public Newsletter(Long designId, String name, String content) {
        this.designId = designId;
        this.name = name;
        this.content = content;
    }

    /**
     * Retrieves the unique design ID of the newsletter.
     *
     * @return the design ID
     */
    public Long getDesignId() {
        return designId;
    }

    /**
     * Sets the unique design ID of the newsletter.
     *
     * @param designId the design ID to set
     */
    public void setDesignId(Long designId) {
        this.designId = designId;
    }

    /**
     * Retrieves the name of the newsletter.
     *
     * @return the name of the newsletter
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the newsletter.
     *
     * @param name the name to set for the newsletter
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the content of the newsletter.
     *
     * @return the content of the newsletter
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the newsletter.
     *
     * @param content the content to set for the newsletter
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Returns a string representation of the Newsletter object.
     *
     * @return a string containing the design ID and content of the newsletter
     */
    @Override
    public String toString() {
        return "Newsletter{" +
                "designId=" + designId +
                ", content='" + content + '\'' +
                '}';
    }
}
