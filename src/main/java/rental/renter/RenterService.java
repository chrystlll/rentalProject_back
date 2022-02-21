package rental.renter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RenterService {

	private final RenterRepository renterRepository;

	@Autowired
	public RenterService(RenterRepository renterRepository) {
		this.renterRepository = renterRepository;
	}

	public List<Renter> getRenter() {
		return renterRepository.findAll();
	}

	public void addNewRenter(Renter renter) {
		renterRepository.save(renter);
	}


}
