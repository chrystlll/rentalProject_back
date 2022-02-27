package rental.mainTenant;

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

	/*** Get the list of main tenants
	 * */
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
	
	/*** Get the main tenant in DB
	 * @param : Email string
	 * If the main tenant doesn't exist it return false
	 * Else it returns true
	 * */
	@GetMapping("/get/email/{email}")
	public Boolean getIsMainTenantExist(@PathVariable String email) {
		Boolean result = mainTenantService.getIsMainTenantExist(email);
		return result;
	}
	
	/*** Get the main tenant in DB
	 * @param : Id long
	 * If the main tenant doesn't exist it return Http.status=NOT_FOUND
	 * Else it returns the main tenant
	 * */
	@GetMapping("/get/id/{id}")
	public ResponseEntity<MainTenant>  getMainTenantById(@PathVariable Long id) {
		ResponseEntity<MainTenant> result = mainTenantService.getMainTenantById(id);
		return result;
	}
	
	/*** Delete the main tenant in DB
	 * @param : id Long	 * */
	@DeleteMapping(path = "{id}")
	private void deleteMainTenant(@PathVariable("id") Long id) {
		mainTenantService.deleteMainTenant(id);
	}
	
	/*** Update the main tenant in DB
	 * @param : mT Maintenant	 * */
	@PostMapping("/post/")
	private void updateMaintenant(@RequestBody MainTenant mT) {
		mainTenantService.updateMaintenant(mT);
	}
	
}