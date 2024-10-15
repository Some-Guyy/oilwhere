package crm.oilwhere.model;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("SALES")
public class SalesTeam extends User {

    public SalesTeam(Long userId, String username, String password, String role) {
        super(userId, username, password, role);
    }

    public SalesTeam() {
        super();
    }

    public void filterPurchaseHistory() {
        //idk yet
    }

    public void viewSalesPerformance() {
        //idk yet
    }

    public void analyseSalesMetrics() {
        //idk yet
    }
}
