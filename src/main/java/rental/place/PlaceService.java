package rental.place;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import rental.enumeration.CommonStatus;
import rental.logger.LOGG;

@Service
public class PlaceService {

	private final PlaceRepository propertyRepository;
	static Logger LOGGER = LOGG.getLogger(PlaceService.class);

	@Autowired
	public PlaceService(PlaceRepository propertyRepository) {
		this.propertyRepository = propertyRepository;
	}

	public List<Place> getPlace() {
		return propertyRepository.findAll();
	}


	public ResponseEntity<List<Place>> getPlaceByStatus(CommonStatus commonstatus) {
		return new ResponseEntity<List<Place>> (propertyRepository.findPlaceByCommonStatus(commonstatus),HttpStatus.OK);
	}
	
	
}
