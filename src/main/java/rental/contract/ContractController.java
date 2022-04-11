package rental.contract;

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

import rental.entities.MainTenantAndContract;
import rental.enumeration.CommonStatus;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(path = "api/v1/contract")
public class ContractController {

	private final ContractService contractService;

	@Autowired
	public ContractController(ContractService contractService) {
		this.contractService = contractService;
	}

	/**
	 * Get all contracts
	 * 
	 * @param: no param
	 * @return: List<Contract>
	 * @apiNote: url : /getAll
	 */
	@GetMapping("/getAll")
	public List<Contract> getContract() {
		return contractService.getContract();
	}

	/**
	 * Get all contracts by Main Tenant Id
	 * 
	 * @param: Id (Long)
	 * @return: ResponseEntity<List<Contract>>
	 * @apiNote: /get/mainTenant/{mTid}
	 */
	@GetMapping("/get/mainTenant/{mTid}")
	public ResponseEntity<List<Contract>> getContractsByMainTenantId(@PathVariable Long mTid) {
		return contractService.getContractsByCriteria(mTid, "mainTenant", "id");
	}

	/**
	 * Get all contracts where commonStatus equal status
	 * 
	 * @param: status (enum)
	 * @return: ResponseEntity<List<Contract>>
	 * @apiNote: /get/status/{status}
	 */
	@GetMapping("/get/status/{status}")
	public ResponseEntity<List<Contract>> getContractsByStatus(@PathVariable CommonStatus status) {
		return contractService.getContractsByCriteria(status, "commonStatus", null);
	}

	/**
	 * Get contract by id
	 * 
	 * @param: id (Long)
	 * @return: ResponseEntity<Contract>
	 * @apiNote: /get/id/{id}
	 */
	@GetMapping("/get/id/{id}")
	public ResponseEntity<Contract> getContractsById(@PathVariable Long id) {
		return contractService.getContractById(id);
	}

	/**
	 * Save or Update the contract
	 * 
	 * @param: mTAndContract (MainTenantAndContract) the json contains the contract
	 *                       object + the mainTenant id
	 * @return: ResponseEntity<Contract>
	 */

	@PostMapping
	public ResponseEntity<Contract> saveContract(@RequestBody MainTenantAndContract mTAndContract) {
		ResponseEntity<Contract> resp = null;
		if(null != mTAndContract.getContract().getId()) {
			resp = contractService.updateContractAndLinkToMT(mTAndContract.getContract(),
					mTAndContract.getMainTenant());
		}else {
			resp = contractService.saveContractAndLinkToMT(mTAndContract.getContract(), mTAndContract.getMainTenant());
		}
		
		return resp;
	}

	/**
	 * Delete the contract
	 * 
	 * @param: id (Long) => contract id
	 * @return: ResponseEntity<Contract>
	 */
	@DeleteMapping(path = "{id}")
	private ResponseEntity<Contract> deleteContract(@PathVariable("id") Long id) {
		// TODO Auto-generated method stub
		ResponseEntity<Contract> resp = contractService.deleteContractById(id);
		return resp;
	}

}
