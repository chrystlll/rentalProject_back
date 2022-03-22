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

	public ResponseEntity<List<Contract>> getContractsByMainTenantId(Long mTid) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Contract> cq = cb.createQuery(Contract.class);
		Root<Contract> contract = cq.from(Contract.class);
		Predicate adPredicate = cb.equal(contract.get("mainTenant").get("id"), mTid);
		cq.where(adPredicate);
		TypedQuery<Contract> query = em.createQuery(cq);
		List<Contract> listContr = query.getResultList();
		return new ResponseEntity<List<Contract>>(listContr, HttpStatus.OK);
	}

	public void addOrUpdateNewContractAndLinkToMT(Contract contract, MainTenant mainTenant) {
		
		/** Update contract case */

		if (null != contract.getId()) {
			Optional<Contract> contractById = contractRepository.findById(contract.getId());
			if (!contractById.isPresent()) {
				// CBN: to be implemented
				throw new RuntimeException();
			} else {
				MainTenant mTenant = mainTenantRepository.getById(mainTenant.getId());
				contract.setMainTenant(mTenant);
				contractRepository.saveAndFlush(contract);
			}
			
		} else {
			/** Create contract case */
			MainTenant mTenant = mainTenantRepository.getById(mainTenant.getId());
			contract.setMainTenant(mTenant);
			contractRepository.save(contract);
		}
		
	}

}
