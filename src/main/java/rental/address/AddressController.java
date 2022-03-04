package rental.address;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rental.entities.MainTenantAndAddress;
import rental.mainTenant.MainTenant;
import rental.mainTenant.MainTenantService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(path = "api/v1/address")
public class AddressController {

	private final AddressService addressService;

	@Autowired
	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}

	/** Get all addresses (no params)*/
	@GetMapping
	public List<Address> getAddresses() {
		return addressService.getAddresses();
	}
	
	/** Get all address by Main Tenant Id
	 * @param: Id (Long)*/
	@GetMapping("/get/mainTenant/{mTid}")
	public ResponseEntity<List<Address>> getAddressesByMainTenantId(@PathVariable Long mTid) {
		return addressService.getAddressesByMainTenantId(mTid);
	}
	
	/** Save or Update the address 
	 * @param: mTAndAddress (MainTenantAndAddress)
	 * the json contains the Address object + the mainTenant id
	 * Note: if this address.getIsPrimary is true 
	 * => All addresses linked to the main tenant have for isPrimary attr value "false"*/ 
	
	@PostMapping
	public void registerAddress(@RequestBody MainTenantAndAddress mTAndAddress) {
		addressService.addOrUpdateNewAddressAndLinkToMT(mTAndAddress.getAddress(),mTAndAddress.getMainTenant());
		
	}

	/** Delete the address
	 * @param: addressId (Long)*/ 
	@DeleteMapping(path = "{addressId}")
	private void deleteAddress(@PathVariable("addressId") Long addressId) {
		// TODO Auto-generated method stub
		addressService.deleteAddress(addressId);
	}

	/* Update the address by id */
	@PutMapping(path = "{addressId}")
	private void updateAddress(@PathVariable("addressId") Long addressId,
			@RequestParam(required = false) String address1) {
		// TODO Auto-generated method stub
		addressService.updateAddress(addressId, address1);
	}
	
	
	

	/* Update of the complete Address entity with all parameters */

//	@PutMapping(path = "{addressId}")
//	private void updateCompleteAddressr Long addressId,
//			@RequestParam(required = false) String address1) {
//		// TODO Auto-generated method stub
//		addressService.updateAddress(addressId, address1);
//	}

}
