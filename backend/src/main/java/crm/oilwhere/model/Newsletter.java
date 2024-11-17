package crm.oilwhere.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

// The following is the Newsletter object to hold each newsletter object
// The newsletter object is for each record in the newsletter table
// It contains the design_id, name and content
// design_id: Id of each newsletter
// name: Name of each customer
// content: Content of the newsletter

@Entity
@Table(name = "newsletter")
public class Newsletter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="design_id")
    private Long designId;

    @Column(name="name")
    private String name;

    @Lob
    @Column(name="content", columnDefinition = "TEXT")
    private String content;

    @Column(name="user_id", nullable=false)
    private Long userId;

    // constructors
    public Newsletter() {}

    public Newsletter(Long designId, String name, String content, Long userId) {
        this.designId = designId;
        this.name = name;
        this.content = content;
        this.userId = userId;
    }

    // getters and Setters
    public Long getDesignId() {
        return designId;
    }

    public void setDesignId(Long designId) {
        this.designId = designId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Newsletter{" +
                "designId=" + designId +
                ", content='" + content + '\'' +
                '}';
    }
}
