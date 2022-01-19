package rental.mainTenant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}