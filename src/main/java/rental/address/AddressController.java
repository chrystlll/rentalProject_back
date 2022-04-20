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

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "api/v1/address")
public class AddressController {

	private final AddressService addressService;

	@Autowired
	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}

	/**
	 * Get all addresses (no params)
	 * 
	 * @param : no params
	 * @return: List<Adress>
	 * @apiNote: /getAll
	 */
	@GetMapping("/getAll")
	public List<Address> getAddresses() {
		return addressService.getAddresses();
	}

	/**
	 * Get all addresses by Main Tenant Id
	 * 
	 * @param: Id (Long)
	 * @return: ResponseEntity<List<Address>> 
	 * @apiNote: /get/mainTenant/{mTid}
	 */
	@GetMapping("/get/mainTenant/{mTid}")
	public ResponseEntity<List<Address>> getAddressesByMainTenantId(@PathVariable Long mTid) {
		return addressService.getAddressesByMainTenantId(mTid);
	}

	/**
	 * Save or Update the address 
	 * @implSpec : AD-01 Note: if this address.getIsPrimary is true  All
	 * addresses linked to the main tenant have for isPrimary attr value "false".
	 * 
	 * @param: mTAndAddress (MainTenantAndAddress) the json witch contains the
	 *                      address object + the mainTenant id
	 * @return ResponseEntity<Address>
	 */
	@PostMapping
	public ResponseEntity<Address> saveAddress(@RequestBody MainTenantAndAddress mTAndAddress) {
		return addressService.addOrUpdateAddressAndLinkToMT(mTAndAddress.getAddress(), mTAndAddress.getMainTenant());
	}

	/**
	 * Delete the address
	 * 
	 * @param: addressId (Long)
	 * @return: ResponseEntity<Address>
	 */
	@DeleteMapping(path = "{addressId}")
	private ResponseEntity<Address> deleteAddress(@PathVariable("addressId") Long addressId) {
		// TODO Auto-generated method stub
		ResponseEntity<Address> add = addressService.deleteAddress(addressId);
		return add ;
	}

	/* Update the address by id */
	@Deprecated
	@PutMapping(path = "{addressId}")
	private void updateAddress(@PathVariable("addressId") Long addressId,
			@RequestParam(required = false) String address1) {
		// TODO Auto-generated method stub
		addressService.updateAddress(addressId, address1);
	}

}
