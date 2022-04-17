package rental.price;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import rental.enumeration.CommonStatus;
import rental.logger.LOGG;
import rental.utils.JpaCriteriaApiUtils;

@Service
public class PriceService {

	private final PriceRepository priceRepository;
	static Logger LOGGER = LOGG.getLogger(PriceService.class);

	@PersistenceContext
	EntityManager em;

	@Autowired
	public PriceService(PriceRepository priceRepositoryRepository) {
		this.priceRepository = priceRepositoryRepository;
	}

	public List<Price> getPrice() {
		return priceRepository.findAll();
	}

	/**
	 * Select the list of prices by criterias
	 * @param: value (Object)
	 * @param: criteria (String) => attribute name
	 * @param: subCriteria (String) => sub-attribute name (ex: price.scheduledPayment.id)
	 * @return ResponseEntity<List<Price>>
	 */
	public ResponseEntity<List<?>> getPricesByCriteria(Object value, String criteria, String subCriteria) {
		ResponseEntity<List<?>> listObj = JpaCriteriaApiUtils.getObjectByCriteria(em, value, criteria, subCriteria,
				Price.class);
		return listObj;
	}

	/**
	 * Delete the current price by id
	 * 
	 * @param: id           (Long)
	 * @return ResponseEntity<Price>
	 */
	public ResponseEntity<Price> deletePrice(Long id) {
		Boolean searchResult = priceRepository.existsById(id);
		if (searchResult) {
			Price price = new Price();
			price = priceRepository.findById(id).get();
			priceRepository.deleteById(id);
			return new ResponseEntity<Price>(price, HttpStatus.OK);
		} else {
			LOGGER.error("The price {} doesn't exist", id);
			return new ResponseEntity<Price>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Return the current active price for a specific contract id
	 * 
	 * @param: id           (Long)
	 * @param: commonStatus (CommonStatus)
	 * @return ResponseEntity<Price>
	 */
	public ResponseEntity<Price> getPricesByContractIdAndStatus(Long id, CommonStatus commonStatus) {
		Price price = priceRepository.findPriceByCommonStatusAndScheduledPayment(commonStatus, id).get();
		if (null != price) {
			return new ResponseEntity<Price>(price, HttpStatus.OK);
		} else {
			LOGGER.error("The price with contractId {} doesn't exist", id);
			return new ResponseEntity<Price>(HttpStatus.NOT_FOUND);
		}
	}
}
