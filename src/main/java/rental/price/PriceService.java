package rental.price;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import rental.contract.Contract;
import rental.enumeration.CommonStatus;
import rental.utils.JpaCriteriaApiUtils;

@Service
public class PriceService {

	private final PriceRepository priceRepository;
	static Logger LOGGER = LogManager.getLogger(PriceService.class);

	@PersistenceContext
	EntityManager em;

	@Autowired
	public PriceService(PriceRepository priceRepositoryRepository) {
		this.priceRepository = priceRepositoryRepository;
	}

	public List<Price> getPrice() {
		return priceRepository.findAll();
	}

	public ResponseEntity<List<?>> getPricesByCriteria(Object value, String criteria, String subCriteria) {
		ResponseEntity<List<?>> listObj = JpaCriteriaApiUtils.getObjectByCriteria(em, value, criteria, subCriteria,
				Price.class);
		return listObj;
	}

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

	public void addOrSavePrice(Contract contract, Price price) {
		

		// check if contract exist
		if (priceRepository.existsById(price.getId())) {
			// Case Update
			price.setContract(contract);
			priceRepository.saveAndFlush(price);
		} else {
			
			if(price.getId() > 0 ) {
				
				System.out.println(">0");
			}else {
				// Case add
				ResponseEntity<List<?>> listObj  = this.getPricesByCriteria(contract.getId(), "contract", "id");
				@SuppressWarnings("unchecked")
				List<Price> listPrice = (List<Price>) listObj.getBody();
				for (Price currentPrice : listPrice) {
					if(null == currentPrice.getEndDate()) {
						currentPrice.setEndDate(new Date());
					}
					currentPrice.setCommonStatus(CommonStatus.INACTIF);
					priceRepository.save(currentPrice);
				}
			}
			price.setContract(contract);
			price.setCommonStatus(CommonStatus.ACTIF);
			priceRepository.save(price);
		}

	}

}
