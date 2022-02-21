package rental.renter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/renter")
public class RenterController {

	
	private final RenterService renterService;

	@Autowired
	public RenterController(RenterService renterService) {
		this.renterService = renterService;
	}

	@GetMapping
	public List<Renter> getRenter() {
		return renterService.getRenter();
	}
	
	@PostMapping
	public void registerRenter(@RequestBody Renter renter) {
		// TODO Auto-generated method stub
		renterService.addNewRenter(renter);
	}
}
