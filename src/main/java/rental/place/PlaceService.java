package rental.place;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rental.logger.LOGG;

@Service
public class PlaceService {

	private final PlaceRepository propertyRepository;
	static Logger LOGGER = LOGG.getLogger(PlaceService.class);

	@Autowired
	public PlaceService(PlaceRepository propertyRepository) {
		this.propertyRepository = propertyRepository;
	}

	public List<Place> getProperty() {
		return propertyRepository.findAll();
	}
}
