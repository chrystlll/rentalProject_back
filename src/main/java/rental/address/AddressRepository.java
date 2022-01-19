package rental.address;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

	@Query("SELECT a FROM Address a WHERE a.address1 = ?1")
	Optional<Address> findAddressByAddress1(String address1);
	
	
}
