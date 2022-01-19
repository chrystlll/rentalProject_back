package rental.vehicle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "api/v1/vehicle")
public class VehicleController {

	

	private final VehicleService renterService;

	@Autowired
	public VehicleController(VehicleService renterService) {
		this.renterService = renterService;
	}

	@GetMapping
	public List<Vehicle> getVehicle() {
		return renterService.getVehicle();
	}
}
