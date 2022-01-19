package rental.vehicle;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class VehicleService {

	private final VehicleRepository vehicleRepository;

	public VehicleService(VehicleRepository vehicleRepository) {
		this.vehicleRepository = vehicleRepository;
	}

	public List<Vehicle> getVehicle() {
		return vehicleRepository.findAll();
	}
}
