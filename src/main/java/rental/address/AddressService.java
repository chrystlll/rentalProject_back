package rental.address;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import rental.enumeration.Gender;
import rental.mainTenant.MainTenant;
import rental.mainTenant.MainTenantRepository;
import rental.renter.Renter;
import rental.renter.RenterRepository;

@Service
public class AddressService {

	private final AddressRepository addressRepository;
	private final MainTenantRepository mainTenantRepository;
	private final RenterRepository renterRepository;

	@Autowired
	public AddressService(RenterRepository renterRepository, AddressRepository addressRepository,
			MainTenantRepository mainTenantRepository) {
		this.addressRepository = addressRepository;
		this.mainTenantRepository = mainTenantRepository;
		this.renterRepository = renterRepository;
	}

	public List<Address> getAddress() {
		return addressRepository.findAll();
	}

	public void addNewAddress(Address address) {
		Optional<Address> addressById = addressRepository.findAddressByAddress1(address.getAddress1());
		MainTenant mt = new MainTenant("ghhgj", Gender.M);
		mainTenantRepository.save(mt);
		Renter renter = new Renter();
		renterRepository.save(renter);
		address.setRenter(renter);
		address.setMainTenant(mt);

		if (addressById.isPresent()) {
			throw new RuntimeException();
		} else {
			addressRepository.save(address);
			System.out.println("Save Done !!!");
		}
	}

	public ResponseEntity<Address> deleteAddress(Long addressId) {
		// TODO Auto-generated method 
		Boolean searchResult = addressRepository.existsById(addressId);
		if(searchResult) {
			Address address = new Address();
			address =  addressRepository.findById(addressId).get();
			addressRepository.deleteById(addressId);
			return new ResponseEntity<Address>(address,HttpStatus.OK);
			
			 
		}else {
			return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
			//throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No resource found for id (%s)", addressId));			    
			
		}
	}

	@Transactional
	public ResponseEntity<Address> updateAddress(Long addressId, String address1) {
		// TODO Auto-generated method stub
		Address address = addressRepository.findById(addressId).orElseThrow();
		address.setAddress1(address1);
		return new ResponseEntity<Address>(address,HttpStatus.OK);
	}

}
