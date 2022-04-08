package rental.payment;

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
import rental.utils.JpaCriteriaApiUtils;

@Service
public class PaymentService {
	@PersistenceContext
	EntityManager em;
	private final PaymentRepository paymentRepository;
	static Logger LOGGER = LogManager.getLogger(PaymentService.class);

	@Autowired
	public PaymentService(PaymentRepository paymentRepository) {
		super();
		this.paymentRepository = paymentRepository;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResponseEntity<List<Payment>> getPaymentsByCriteria(Long id, String criteria, String subcriteria) {
		List<Payment> respListPayment = (List<Payment>) JpaCriteriaApiUtils
				.getObjectByCriteria(em, id, criteria, subcriteria, Payment.class).getBody();
		return new ResponseEntity(respListPayment, HttpStatus.OK);
	}

	public void addOrUpdatePayment(Contract contract, Payment payment) {
		// check if contract exist
		if (null != payment.getId()) {

			if (paymentRepository.existsById(payment.getId())) {
				// Case Update
				payment.setContract(contract);
				paymentRepository.saveAndFlush(payment);
			} else {

			}
		} else {
			// Case add
			payment.setContract(contract);
			paymentRepository.save(payment);
		}
	}
}
