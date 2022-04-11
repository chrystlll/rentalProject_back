package rental.vehicle;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

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
}
