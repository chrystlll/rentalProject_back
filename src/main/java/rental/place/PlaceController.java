package rental.place;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "api/v1/property")
public class PlaceController {


	private final PlaceService propertyService;

	@Autowired
	public PlaceController(PlaceService propertyService) {
		this.propertyService = propertyService;
	}

	@GetMapping
	public List<Place> getProperty() {
		return propertyService.getProperty();
	}
}
