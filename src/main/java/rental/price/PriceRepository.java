package rental.price;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long>{

	
	/**Select price by status for a specific contract
	 * @param commonStatus (CommonStatus) of the price
	 * @param id (Long) of contract*/
	@Query(value="SELECT pr.* FROM Price pr, Scheduled_Payment sp, contract c where pr.common_Status in (:commonStatus) and c.id=:id group by c.id",nativeQuery=true)
	Optional<Price> findPriceByCommonStatusAndScheduledPayment(@Param("commonStatus")String commonStatus, @Param("id")Long id);

	
	
}
