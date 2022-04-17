package rental.scheduledPayment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduledPaymentRepository extends JpaRepository<ScheduledPayment, Long>{

	
	
}
