package rental.mainTenant;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import rental.logger.LOGG;

@Service
public class MainTenantService {

	private final MainTenantRepository mainTenantRepository;
	static Logger LOGGER = LOGG.getLogger(MainTenantService.class);

	@PersistenceContext
	EntityManager em;

	@Autowired
	public MainTenantService(MainTenantRepository mainTenantRepository) {
		this.mainTenantRepository = mainTenantRepository;
	}

	public List<MainTenant> getMainTenant() {
		return mainTenantRepository.findAll();
	}

	public void addNewTenant(MainTenant mT) {
		mainTenantRepository.save(mT);

	}

	public Boolean getIsMainTenantExist(String email) {
		Boolean result = false;
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<MainTenant> cq = cb.createQuery(MainTenant.class);
		Root<MainTenant> mainTenant = cq.from(MainTenant.class);
		Predicate mTPredicate = cb.equal(mainTenant.get("email"), email);
		cq.where(mTPredicate);
		TypedQuery<MainTenant> query = em.createQuery(cq);
		List<MainTenant> listMT = query.getResultList();
		if (0 != listMT.size()) {
			result = true;
		}
		return result;
	}

	public ResponseEntity<MainTenant> deleteMainTenant(Long mTId) {
		Boolean searchResult = mainTenantRepository.existsById(mTId);
		if (searchResult) {
			MainTenant mt = new MainTenant();
			mt = mainTenantRepository.findById(mTId).get();
			mainTenantRepository.deleteById(mTId);
			return new ResponseEntity<MainTenant>(mt, HttpStatus.OK);
		} else {
			LOGGER.error("The main tenant {} doesn't exist", mTId);
			return new ResponseEntity<MainTenant>(HttpStatus.NOT_FOUND);

		}
	}

	public ResponseEntity<MainTenant> getMainTenantById(Long id) {
		Boolean searchResult = mainTenantRepository.existsById(id);
		if (searchResult) {
			MainTenant mt = new MainTenant();
			mt = mainTenantRepository.findById(id).get();
			return new ResponseEntity<MainTenant>(mt, HttpStatus.OK);
		} else {
			LOGGER.error("The main tenant {} doesn't exist", id);
			return new ResponseEntity<MainTenant>(HttpStatus.NOT_FOUND);

		}
	}

	public ResponseEntity<MainTenant> updateMaintenant(MainTenant mt) {

		Boolean searchResult = mainTenantRepository.existsById(mt.getId());
		if (searchResult) {
			mt.setContracts(mainTenantRepository.findById(mt.getId()).get().getContracts());
			mt.setAddresses(mainTenantRepository.findById(mt.getId()).get().getAddresses());
			mt = mainTenantRepository.saveAndFlush(mt);
			return new ResponseEntity<MainTenant>(mt, HttpStatus.OK);
		} else {
			LOGGER.error("The main tenant {} doesn't exist", mt.getId());
			return new ResponseEntity<MainTenant>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<List<MainTenant>> getMainTenantByCriteria(Object search, String criteria,
			String subCriteria) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<MainTenant> cq = cb.createQuery(MainTenant.class);
		Root<MainTenant> maintenant = cq.from(MainTenant.class);
		Predicate adPredicate;
		if (null != subCriteria) {
			adPredicate = cb.equal(maintenant.join("contracts").get("id"), search);
		} else {
			adPredicate = cb.equal(maintenant.get(criteria), search);
		}
		cq.where(adPredicate);
		TypedQuery<MainTenant> query = em.createQuery(cq);
		List<MainTenant> listMT = query.getResultList();
		return new ResponseEntity<List<MainTenant>>(listMT, HttpStatus.OK);
	}
}
