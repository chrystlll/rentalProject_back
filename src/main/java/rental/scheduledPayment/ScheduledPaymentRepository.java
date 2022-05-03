package rental.scheduledPayment;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduledPaymentRepository extends JpaRepository<ScheduledPayment, Long>{

	
	/**Select schPayment by status for a specific contract
	 * @param commonStatus (CommonStatus) of the schPayment
	 * @param id (Long) of contract*/
	@Query(value="SELECT sp.* FROM Scheduled_Payment sp, contract c where sp.common_status in (:commonStatus) and c.id=:id",nativeQuery=true)
	Optional<List<ScheduledPayment>> findSchedPaymentByCommonStatusAndScheduledPayment(@Param("commonStatus")String commonStatus, @Param("id")Long id);

}
