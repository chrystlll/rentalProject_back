package rental.address;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import rental.logger.LOGG;
import rental.mainTenant.MainTenant;
import rental.mainTenant.MainTenantRepository;
import rental.utils.JpaCriteriaApiUtils;
import rental.utils.RentalMessage;

@Service
public class AddressService {
	@PersistenceContext
	EntityManager em;

	private final AddressRepository addressRepository;
	private final MainTenantRepository mainTenantRepository;
	static Logger LOGGER = LOGG.getLogger(AddressService.class);

	@Autowired
	public AddressService(AddressRepository addressRepository, MainTenantRepository mainTenantRepository) {
		super();
		this.addressRepository = addressRepository;
		this.mainTenantRepository = mainTenantRepository;
	}

	/** Return all address from DB
	 * @param: address (Address)
	 * @return: List<Address>*/
	public List<Address> getAddresses() {
		return addressRepository.findAll();
	}
	
	/** Add the current address on DB
	 * @param: address (Address)
	 * @return:ResponseEntity<Address>*/
	public ResponseEntity<Address> addAddress(Address address){
		Address add = this.addressRepository.save(address);
		if(null != add) {
			LOGGER.info(RentalMessage.entitySaved,add.getId());
			return new ResponseEntity<Address>(address,HttpStatus.OK);
		}else {
			LOGGER.error(RentalMessage.entityNotSaved, address);
			return new ResponseEntity<Address>(address,HttpStatus.SERVICE_UNAVAILABLE);
		}
		
	}

	public ResponseEntity<Address> addOrUpdateAddressAndLinkToMT(Address address, MainTenant mt) {
		/** Update address case */
		if (null != address.getId()) {
			if (!addressRepository.existsById(address.getId())) {
				// Entity is not found
				LOGGER.error(RentalMessage.entityNotFound, address.getId());
				return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);

			} else {
				// Entity is found
				MainTenant mTenant = mainTenantRepository.getById(mt.getId());
				address.setMainTenant(mTenant);
				updateAdressIsPrimary(address, mt.getId());
				addressRepository.save(address);
				LOGGER.info(RentalMessage.entitySaved, address.getId());
				return new ResponseEntity<Address>(address, HttpStatus.OK);
			}
		} else {
			/** Create address case */
			MainTenant mTenant = mainTenantRepository.getById(mt.getId());
			address.setMainTenant(mTenant);
			updateAdressIsPrimary(address, mt.getId());
			addressRepository.save(address);
			LOGGER.info(RentalMessage.entitySaved, address.getId());
			return new ResponseEntity<Address>(address, HttpStatus.CREATED);
		}
	}

	/**
	 * Update the value "Is Primary" on addresses witch are linked to the current
	 * mainTenant
	 * 
	 * @param: address: Address (current address created or updated)
	 * @param: id:      Long (current main tenant id)
	 * 
	 * @return : no return
	 */
	@SuppressWarnings("unchecked")
	private void updateAdressIsPrimary(Address address, Long mTId) {
		// Control if the address is primary
		if (null != address.getIsPrimary()) {
			if (true == address.getIsPrimary()) {
				List<Address> listAddress = (List<Address>) JpaCriteriaApiUtils
						.getObjectByCriteria(em, mTId, "mainTenant", "id", Address.class).getBody();
				if (listAddress.size() > 0) {
					List<Address> addrToModif = new ArrayList<Address>();
					for (Address address2 : listAddress) {
						if (true == address2.getIsPrimary() || null == address2.getIsPrimary()) {
							address2.setIsPrimary(false);
							addrToModif.add(address2);
						}
					}
					if (addrToModif.size() > 0) {
						addressRepository.saveAll(addrToModif);
					}
				}
			}
		}
	}

	/**
	 * Delete the current address
	 * 
	 * @param: id (Long) (current address id)
	 * @return : ResponseEntity<Address>
	 */
	public ResponseEntity<Address> deleteAddress(Long id) {
		// TODO Auto-generated method
		Boolean searchResult = addressRepository.existsById(id);
		if (searchResult) {
			Address address = new Address();
			address = addressRepository.findById(id).get();
			addressRepository.deleteById(id);
			LOGGER.info(RentalMessage.entityDeleted, id);
			return new ResponseEntity<Address>(address, HttpStatus.OK);

		} else {
			LOGGER.error(RentalMessage.entityNotDeleted, id);
			return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
		}
	}

	/** @deprecated */
	@Transactional
	public ResponseEntity<Address> updateAddress(Long addressId, String address1) {
		// TODO Auto-generated method stub
		Address address = addressRepository.findById(addressId).orElseThrow();
		address.setAddress1(address1);
		return new ResponseEntity<Address>(address, HttpStatus.OK);
	}

	/**
	 * Select the address for this specific mainTenant
	 * @param: id (Long) (current mainTenant id)
	 * @return : ResponseEntity<List<Address>>
	 */
	public ResponseEntity<List<Address>> getAddressesByMainTenantId(Long id) {
		List<Address> listAd = addressRepository.findAddressByMainTenantId(id);
		return new ResponseEntity<List<Address>>(listAd, HttpStatus.OK);
	}
}
