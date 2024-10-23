package crm.oilwhere.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import crm.oilwhere.model.Filter;

@Repository
public interface FilterRepository extends JpaRepository<Filter, Long>{
    
    List<Filter> findAllByOrderByTotalSpendingDesc();
    List<Filter> findAllByOrderByTotalSpending();

}
