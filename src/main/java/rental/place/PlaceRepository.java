package rental.place;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rental.enumeration.CommonStatus;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

	
	@Query("SELECT p FROM Place p WHERE p.comon_status = ?1")
	List<Place> findPlaceByCommonStatus(CommonStatus commonStatus);
	
}
