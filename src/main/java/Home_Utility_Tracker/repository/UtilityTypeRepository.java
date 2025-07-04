package Home_Utility_Tracker.repository;

import Home_Utility_Tracker.model.UtilityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilityTypeRepository extends JpaRepository<UtilityType, Long> {
}
