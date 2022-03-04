package rental.address;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import rental.mainTenant.MainTenant;
import rental.mainTenant.MainTenantRepository;

@Service
public class AddressService {
	@PersistenceContext
	EntityManager em;

	private final AddressRepository addressRepository;
	private final MainTenantRepository mainTenantRepository;

	@Autowired
	public AddressService(AddressRepository addressRepository, MainTenantRepository mainTenantRepository) {
		super();
		this.addressRepository = addressRepository;
		this.mainTenantRepository = mainTenantRepository;
	}

	public List<Address> getAddresses() {
		return addressRepository.findAll();
	}

	public void addOrUpdateNewAddressAndLinkToMT(Address address, MainTenant mt) {

		/** Update address case */

		if (null != address.getId()) {
			Optional<Address> addressById = addressRepository.findById(address.getId());
			if (!addressById.isPresent()) {
				// CBN: to be implemented
				throw new RuntimeException();
			} else {
				MainTenant mTenant = mainTenantRepository.getById(mt.getId());
				address.setMainTenant(mTenant);
				updateAdressIsPrimary(address, mt.getId());
				addressRepository.saveAndFlush(address);
			}
			
		} else {
			/** Create address case */
			MainTenant mTenant = mainTenantRepository.getById(mt.getId());
			address.setMainTenant(mTenant);
			updateAdressIsPrimary(address, mt.getId());
			addressRepository.save(address);

		}
	}

	/**
	 * Update the value "Is Primary" on addresses witch are linked to the current
	 * mainTenant
	 * 
	 * @param: address: Address (current address created or updated)
	 * @param: id:      Long (current main tenant id)
	 */
	private void updateAdressIsPrimary(Address address, Long mTId) {

		if (null != address.getIsPrimary()) {
			if (true == address.getIsPrimary()) {
				CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<Address> cq = cb.createQuery(Address.class);
				Root<Address> add = cq.from(Address.class);
				Predicate addPredicate = cb.equal(add.get("mainTenant").get("id"), mTId);
				cq.where(addPredicate);
				TypedQuery<Address> query = em.createQuery(cq);

				List<Address> mTAddresses = query.getResultList();
				if (mTAddresses.size() > 0) {
					List<Address> addrToModif = new ArrayList<Address>();
					for (Address address2 : mTAddresses) {
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

	public ResponseEntity<Address> deleteAddress(Long addressId) {
		// TODO Auto-generated method
		Boolean searchResult = addressRepository.existsById(addressId);
		if (searchResult) {
			Address address = new Address();
			address = addressRepository.findById(addressId).get();
			addressRepository.deleteById(addressId);
			return new ResponseEntity<Address>(address, HttpStatus.OK);

		} else {
			return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
			// throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No
			// resource found for id (%s)", addressId));

		}
	}

	@Transactional
	public ResponseEntity<Address> updateAddress(Long addressId, String address1) {
		// TODO Auto-generated method stub
		Address address = addressRepository.findById(addressId).orElseThrow();
		address.setAddress1(address1);
		return new ResponseEntity<Address>(address, HttpStatus.OK);
	}

	public ResponseEntity<List<Address>> getAddressesByMainTenantId(Long id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Address> cq = cb.createQuery(Address.class);
		Root<Address> address = cq.from(Address.class);
		Predicate adPredicate = cb.equal(address.get("mainTenant").get("id"), id);
		cq.where(adPredicate);
		TypedQuery<Address> query = em.createQuery(cq);
		List<Address> listAd = query.getResultList();
		return new ResponseEntity<List<Address>>(listAd, HttpStatus.OK);
	}

}
