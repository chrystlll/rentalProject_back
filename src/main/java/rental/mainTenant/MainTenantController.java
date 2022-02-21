package rental.mainTenant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rental.enumeration.TenantStatus;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(path = "api/v1/mainTenant")
public class MainTenantController {

	private final MainTenantService mainTenantService;

	@Autowired
	public MainTenantController(MainTenantService mainTenantService) {
		this.mainTenantService = mainTenantService;
	}

	@GetMapping
	public List<MainTenant> getMainTenant() {
		return mainTenantService.getMainTenant();
	}
	
	/*** Register the main tenant in DB
	 * @param : MainTenant object
	 * By default the status is ACTIVE 
	 * */
	@PostMapping
	public void registerMainTenant(@RequestBody MainTenant mT) {
		mT.setMainTenantStatus(TenantStatus.ACTIF.toString());
		mainTenantService.addNewTenant(mT);
	}
}