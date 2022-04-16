package rental.vehicle;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import rental.contract.Contract;
import rental.logger.LOGG;
import rental.scheduledPayment.ScheduledPaymentService;

@Service
public class VehicleService {

	private final VehicleRepository vehicleRepository;
	static Logger LOGGER = LOGG.getLogger(ScheduledPaymentService.class);
	
	public VehicleService(VehicleRepository vehicleRepository) {
		this.vehicleRepository = vehicleRepository;
	}

	public List<Vehicle> getVehicle() {
		return vehicleRepository.findAll();
	}

	public ResponseEntity<List<Vehicle>> getVehicleByContractId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseEntity<Vehicle> addOrUpdateVehicleAndLinkToContract(Vehicle vehicle, Contract contract) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseEntity<Vehicle> deleteVehicle(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
