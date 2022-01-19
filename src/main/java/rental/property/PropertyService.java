package rental.property;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {

	private final PropertyRepository propertyRepository;

	@Autowired
	public PropertyService(PropertyRepository propertyRepository) {
		this.propertyRepository = propertyRepository;
	}

	public List<Property> getProperty() {
		return propertyRepository.findAll();
	}
}
