package rental.contract;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import rental.mainTenant.MainTenant;
import rental.mainTenant.MainTenantRepository;

@Service
public class ContractService {
	@PersistenceContext
	EntityManager em;

	private final ContractRepository contractRepository;
	private final MainTenantRepository mainTenantRepository;

	@Autowired
	public ContractService(ContractRepository contractRepository, MainTenantRepository mainTenantRepository) {
		super();
		this.contractRepository = contractRepository;
		this.mainTenantRepository = mainTenantRepository;
	}

	public List<Contract> getContract() {
		return contractRepository.findAll();
	}

	public void addOrUpdateNewContractAndLinkToMT(Contract contract, MainTenant mainTenant) {

		/** Update contract case */

		if (null != contract.getId()) {
			Optional<Contract> contractById = contractRepository.findById(contract.getId());
			if (!contractById.isPresent()) {
				// CBN: to be implemented
				throw new RuntimeException();
			} else {
				contract.setPayment(contractRepository.getById(contract.getId()).getPayment());				
				contract.setPrice(contractRepository.getById(contract.getId()).getPrice());
				if (null != mainTenant) {
					MainTenant mTenant = mainTenantRepository.getById(mainTenant.getId());
					contract.setMainTenant(mTenant);
				}else {
					MainTenant mTenant = contractRepository.findById(contract.getId()).get().getMainTenant();
					contract.setMainTenant(mTenant);
				}
				contractRepository.save(contract);
			}

		} else {
			/** Create contract case */
			MainTenant mTenant = mainTenantRepository.getById(mainTenant.getId());
			contract.setMainTenant(mTenant);
			contractRepository.save(contract);
		}

	}

	public ResponseEntity<Contract> deleteId(Long id) {
		// TODO Auto-generated method stub
		Boolean searchResult = contractRepository.existsById(id);
		if (searchResult) {
			Contract contract = new Contract();
			contract = contractRepository.findById(id).get();
			contractRepository.deleteById(id);
			return new ResponseEntity<Contract>(contract, HttpStatus.OK);

		} else {
			return new ResponseEntity<Contract>(HttpStatus.NOT_FOUND);
			// throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No
			// resource found for id (%s)", addressId));

		}
	}

	public ResponseEntity<List<Contract>> getContractsByCriteria(Object status, String criteria, String subCriteria) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Contract> cq = cb.createQuery(Contract.class);
		Root<Contract> contract = cq.from(Contract.class);
		Predicate adPredicate;
		if (null != subCriteria) {
			adPredicate = cb.equal(contract.get(criteria).get(subCriteria), status);
		} else {
			adPredicate = cb.equal(contract.get(criteria), status);
		}
		cq.where(adPredicate);
		TypedQuery<Contract> query = em.createQuery(cq);
		List<Contract> listContr = query.getResultList();
		return new ResponseEntity<List<Contract>>(listContr, HttpStatus.OK);
	}
	
	public ResponseEntity<Contract> getContractById(Long id){
		
		Contract contract = new Contract();
		contract = contractRepository.findById(id).get();
		if(null != contract) {
			return new ResponseEntity<Contract>(contract, HttpStatus.OK);
		}else {
			return new ResponseEntity<Contract>(HttpStatus.NOT_FOUND);
		}
		
		
	}

}
