package rental.place;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rental.enumeration.CommonStatus;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "api/v1/place")
public class PlaceController {


	private final PlaceService placeService;

	@Autowired
	public PlaceController(PlaceService placeService) {
		this.placeService = placeService;
	}

	/**
	 * Get all places (no params)
	 * 
	 * @param : no params
	 * @return: List<Place>
	 * @apiNote: /getAll
	 */
	@GetMapping("/getAll")
	public List<Place> getPlace() {
		return placeService.getPlace();
	}
	
	
	/**
	 * Get all places by common status
	 * 
	 * @param : commonStatus
	 * @return: ResponseEntity<Place>
	 * @apiNote: "/get/commonStatus/{commonStatus}"
	 */
	@GetMapping("/get/commonStatus/{commonStatus}")
	public ResponseEntity<List<Place>> getPlaceByStatus(@PathVariable CommonStatus commonStatus){
		return placeService.getPlaceByStatus(commonStatus);
	}
}
