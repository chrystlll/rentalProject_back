package rental.mainTenant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainTenantRepository extends JpaRepository<MainTenant, Long> {

	
}
