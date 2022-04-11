package rental.scheduledPayment;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import rental.contract.Contract;
import rental.logger.LOGG;
import rental.utils.JpaCriteriaApiUtils;

@Service
public class ScheduledPaymentService {
	@PersistenceContext
	EntityManager em;
	private final ScheduledPaymentRepository paymentRepository;
	static Logger LOGGER = LOGG.getLogger(ScheduledPaymentService.class);

	@Autowired
	public ScheduledPaymentService(ScheduledPaymentRepository paymentRepository) {
		super();
		this.paymentRepository = paymentRepository;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResponseEntity<List<ScheduledPayment>> getPaymentsByCriteria(Long id, String criteria, String subcriteria) {
		List<ScheduledPayment> respListPayment = (List<ScheduledPayment>) JpaCriteriaApiUtils
				.getObjectByCriteria(em, id, criteria, subcriteria, ScheduledPayment.class).getBody();
		return new ResponseEntity(respListPayment, HttpStatus.OK);
	}

	public void addOrUpdatePayment(Contract contract, ScheduledPayment payment) {
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
