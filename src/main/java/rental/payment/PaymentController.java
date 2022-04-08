package rental.payment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rental.entities.PaymentAndContract;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(path = "api/v1/payment")
public class PaymentController {

	private final PaymentService paymentService;

	@Autowired
	public PaymentController(PaymentService paymentService) {
		super();
		this.paymentService = paymentService;
	}

	/**
	 * Get all payments by contract Id
	 * 
	 * @param: Id (Long)
	 */
	@GetMapping("/get/contract/{id}")
	public ResponseEntity<List<Payment>> getPaymentsByContractId(@PathVariable Long id) {
		return paymentService.getPaymentsByCriteria(id, "contract", "id");
	}

	/**
	 * Save or Update the payment
	 * 
	 * @param: paymentAndContract (PaymentAndContract) the json contains the payment
	 *                       object + the contract id
	 */

	@PostMapping
	public void registerPayment(@RequestBody PaymentAndContract paymentAndContract) {
		paymentService.addOrUpdatePayment(paymentAndContract.getContract(), paymentAndContract.getPayment());

	}

}
