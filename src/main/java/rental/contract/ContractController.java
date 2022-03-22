package rental.contract;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rental.entities.MainTenantAndContract;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(path = "api/v1/contract")
public class ContractController {

	private final ContractService contractService;

	@Autowired
	public ContractController(ContractService contractService) {
		this.contractService = contractService;
	}

	@GetMapping
	public List<Contract> getContract() {
		return contractService.getContract();
	}
	
	/** Get all contracts by Main Tenant Id
	 * @param: Id (Long)*/
	@GetMapping("/get/mainTenant/{mTid}")
	public ResponseEntity<List<Contract>> getContractsByMainTenantId(@PathVariable Long mTid) {
		return contractService.getContractsByMainTenantId(mTid);
	
	}
	
	/** Save or Update the contract 
	 * @param: mTAndContract (MainTenantAndContract)
	 * the json contains the contract object + the mainTenant id*/ 
	
	@PostMapping
	public void registerAddress(@RequestBody MainTenantAndContract mTAndContract) {
		contractService.addOrUpdateNewContractAndLinkToMT(mTAndContract.getContract(),mTAndContract.getMainTenant());
		
	}
	
	
	
}
