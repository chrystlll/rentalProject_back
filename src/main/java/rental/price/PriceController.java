package rental.price;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rental.entities.PriceAndContractId;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "api/v1/price")
public class PriceController {

	private final PriceService priceService;

	@Autowired
	public PriceController(PriceService priceService) {
		this.priceService = priceService;
	}

	/**
	 * Get all prices
	 * 
	 * @param: no param
	 * @return List<Price>
	 */
	@GetMapping("/get/all/")
	public List<Price> getPrice() {
		return priceService.getPrice();
	}

	/**
	 * Get the price where contractId equals id and commonStatus equals commonStatus
	 * 
	 * @param: id           (long)
	 * @param: commonStatus (CommonStatus)
	 * @return ResponseEntity<Price>
	 */
	@GetMapping("/get/contractId/{id}/commonStatus/{commonStatus}")
	public ResponseEntity<Price> getPricesByContractId(@PathVariable Long id, @PathVariable String commonStatus) {
		return priceService.getPricesByContractIdAndStatus(id, commonStatus);
	}

	/***
	 * Delete the price
	 * 
	 * @param : id Long *
	 */
	@DeleteMapping(path = "{id}")
	private void deletePrice(@PathVariable("id") Long id) {
		priceService.deletePrice(id);
	}

	/**
	 * Save or update the current price
	 * 
	 * @param: price (Price)
	 * @param: contractId (Long)
	 * @return: ResponseEntity<Price>
	 */
	@PostMapping
	private ResponseEntity<Price> savePrice(@RequestBody PriceAndContractId priceAndContractId) {
		
		ResponseEntity<Price> response = priceService.saveOrUpdatePrice(priceAndContractId.getPrice(),priceAndContractId.getContractId());
		return response;
	}

}
