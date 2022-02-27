package rental.place;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaceService {

	private final PlaceRepository propertyRepository;

	@Autowired
	public PlaceService(PlaceRepository propertyRepository) {
		this.propertyRepository = propertyRepository;
	}

	public List<Place> getProperty() {
		return propertyRepository.findAll();
	}
}
