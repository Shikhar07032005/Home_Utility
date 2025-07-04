package Home_Utility_Tracker.repository;

import Home_Utility_Tracker.model.User;
import Home_Utility_Tracker.model.UtilityBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilityBillRepository extends JpaRepository<UtilityBill, Long> {
    List<UtilityBill> findByUser(Optional<User> user);
}
