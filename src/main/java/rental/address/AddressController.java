package rental.address;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/address")
public class AddressController {

	private final AddressService addressService;

	@Autowired
	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}

	@GetMapping
	public List<Address> getAddress() {
		return addressService.getAddress();
	}
	

	@PostMapping
	public void registerAddress(@RequestBody Address address) {
		// TODO Auto-generated method stub
		addressService.addNewAddress(address);
	}

	@DeleteMapping(path = "{addressId}")
	private void deleteAddress(@PathVariable("addressId") Long addressId) {
		// TODO Auto-generated method stub
		addressService.deleteAddress(addressId);
	}

	/* Update of Address entity by address1 */

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
