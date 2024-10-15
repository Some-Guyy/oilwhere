package crm.oilwhere.model;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("MARKETING")
public class MarketingTeam extends User {
    
    public MarketingTeam(Long userId, String username, String password, String role) {
        super(userId, username, password, role);
    }

    public MarketingTeam() {
        super();
    }

    public void filterPurchaseHistory() {
        //idk yet
    }

    public void createSegmentedLists() {
        //idk yet
    }

    public void designNewsLetter() {
        //idk yet
    }
}
