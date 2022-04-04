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

import rental.entities.PriceAndContract;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(path = "api/v1/price")
public class PriceController {

	private final PriceService priceService;

	@Autowired
	public PriceController(PriceService priceService) {
		this.priceService = priceService;
	}

	@GetMapping("/get/all/")
	public List<Price> getPrice() {
		return priceService.getPrice();
	}

	/**
	 * Get all price where contractId equal id
	 * 
	 * @param: id (long)
	 */
	@GetMapping("/get/contractId/{id}")
	public ResponseEntity<List<?>> getPricesByContractId(@PathVariable Long id) {
		return priceService.getPricesByCriteria(id, "contract", "id");
	}

	/***
	 * Delete the price in DB
	 * 
	 * @param : id Long *
	 */
	@DeleteMapping(path = "{id}")
	private void deletePrice(@PathVariable("id") Long id) {
		priceService.deletePrice(id);
	}
	
	
	/** Save or Update the price 
	 * @param: price (Price)
	 * Note: if price has an id => not update contract entity linked
	 * Note: if price is a new price => set Now() on endDate attribute value without any value (same contract id)
	 * and set Inactif as common status attr value for all contracts (same contract id)
	 */ 
	@PostMapping
	public void registerPrice(@RequestBody PriceAndContract priceAndContract) {
		priceService.addOrSavePrice(priceAndContract.getContract(),priceAndContract.getPrice());
		
	}
}
