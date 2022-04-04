package rental.utils;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class JpaCriteriaApiUtils {
	
	
	@SuppressWarnings("unchecked")
	public static ResponseEntity<List<?>> getObjectByCriteria(EntityManager em, Object value, String criteria, String subCriteria, Class currentObject) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<?> cq = cb.createQuery(currentObject);
		Root<?> classSearch = cq.from(currentObject);
		Predicate adPredicate;
		if (null != subCriteria) {
			adPredicate = cb.equal(classSearch.get(criteria).get(subCriteria), value);
		} else {
			adPredicate = cb.equal(classSearch.get(criteria), value);
		}
		cq.where(adPredicate);
		TypedQuery<?> query = em.createQuery(cq);
		List<?> listObjects = query.getResultList();
		return new ResponseEntity<List<?>>(listObjects, HttpStatus.OK);
		
	};
	

}
