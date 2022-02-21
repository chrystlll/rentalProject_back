package rental.mainTenant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainTenantService {

	private final MainTenantRepository mainTenantRepository;

	@Autowired
	public MainTenantService(MainTenantRepository mainTenantRepository) {
		this.mainTenantRepository = mainTenantRepository;
	}

	public List<MainTenant> getMainTenant() {
		return mainTenantRepository.findAll();
	}
	
	public void addNewTenant(MainTenant mT) {
		mainTenantRepository.save(mT);
		
	}

}
