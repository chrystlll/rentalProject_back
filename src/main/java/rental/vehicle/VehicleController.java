package rental.vehicle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rental.entities.VehicleAndContract;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "api/v1/vehicle")
public class VehicleController {

	private final VehicleService vehicleService;

	@Autowired
	public VehicleController(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}

	/**
	 * Get all vehicles (no params)
	 * 
	 * @param : no params
	 * @return: List<Vehicle>
	 * @apiNote: /getAll
	 */
	@GetMapping("/getAll")
	public List<Vehicle> getVehicle() {
		return vehicleService.getVehicle();
	}
	
	
	/**
	 * Get all vehicles by contract Id
	 * 
	 * @param: Id (Long)
	 * @return: ResponseEntity<List<Vehicle>> 
	 * @apiNote: /get/contract/{id}
	 */
	@GetMapping("/get/contract/{id}")
	public ResponseEntity<List<Vehicle>> getVehicleByContractId(@PathVariable Long id) {
		return vehicleService.getVehicleByContractId(id);
	}
	
	
	/**
	 * Save or Update the vehicle 
	 * 
	 * @param: vehicleAndContract (VehicleAndContract) the json witch contains the
	 *                      vehicle object + the contract id
	 * @return ResponseEntity<Vehicle>
	 */
	@PostMapping
	public ResponseEntity<Vehicle> saveVehicle(@RequestBody VehicleAndContract vehicleAndContract) {
		return vehicleService.addOrUpdateVehicleAndLinkToContract(vehicleAndContract.getVehicle(), vehicleAndContract.getContract());
	}

	/**
	 * Delete the vehicle
	 * 
	 * @param: id (Long)
	 * @return: ResponseEntity<Vehicle>
	 */
	@DeleteMapping(path = "{id}")
	private ResponseEntity<Vehicle> deleteAddress(@PathVariable("id") Long id) {
		// TODO Auto-generated method stub
		ResponseEntity<Vehicle> vehicle = vehicleService.deleteVehicle(id);
		return vehicle ;
	}

}
