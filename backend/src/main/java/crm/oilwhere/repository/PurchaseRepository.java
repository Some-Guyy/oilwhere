package crm.oilwhere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import crm.oilwhere.model.Purchase;

import java.util.List;
import java.time.LocalDate;



@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long>{
    
    List<Purchase> findBySaleDateBetween(LocalDate startDate, LocalDate endDate);
}
