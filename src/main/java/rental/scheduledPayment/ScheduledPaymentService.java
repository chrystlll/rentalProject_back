package rental.scheduledPayment;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import rental.contract.Contract;
import rental.contract.ContractRepository;
import rental.enumeration.CommonStatus;
import rental.enumeration.Currency;
import rental.enumeration.DurationType;
import rental.enumeration.PaymentStatus;
import rental.enumeration.ScheduledPaymentType;
import rental.logger.LOGG;
import rental.price.Price;
import rental.price.PriceRepository;
import rental.utils.JpaCriteriaApiUtils;
import rental.utils.RentalMessage;
import rental.utils.Utils;

@Service
public class ScheduledPaymentService {
	@PersistenceContext
	EntityManager em;
	private final ScheduledPaymentRepository paymentRepository;
	private final PriceRepository priceRepository;
	private final ContractRepository contractRepository;
	static Logger LOGGER = LOGG.getLogger(ScheduledPaymentService.class);

	@Autowired
	public ScheduledPaymentService(ScheduledPaymentRepository paymentRepository, PriceRepository priceRepository,
			ContractRepository contractRepository) {
		super();
		this.paymentRepository = paymentRepository;
		this.priceRepository = priceRepository;
		this.contractRepository = contractRepository;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResponseEntity<List<ScheduledPayment>> getPaymentsByCriteria(Long id, String criteria, String subcriteria) {
		List<ScheduledPayment> respListPayment = (List<ScheduledPayment>) JpaCriteriaApiUtils
				.getObjectByCriteria(em, id, criteria, subcriteria, ScheduledPayment.class).getBody();
		return new ResponseEntity(respListPayment, HttpStatus.OK);
	}

	/**
	 * Add or update the scheduledPayment
	 * 
	 * @param: contrat          (Contract) contains id
	 * @param: scheduledPayment (ScheduledPayment)
	 * @return: ResponseEntity<ScheduledPayment>
	 * @implSpec: SCP-01 and SCP-02
	 */
	public ResponseEntity<ScheduledPayment> addOrUpdatePayment(Contract contract, ScheduledPayment scheduledPayment) {
		if (null != scheduledPayment.getId()) {
			// Case update
			if (paymentRepository.existsById(scheduledPayment.getId())) {
				ScheduledPayment schPay = paymentRepository.findById(scheduledPayment.getId()).get();
				scheduledPayment.setPrice(schPay.getPrice());
				scheduledPayment.setCurrency(Currency.€);
				if (null != contract) {
					scheduledPayment.setContract(contract);
				} else {
					scheduledPayment.setContract(schPay.getContract());
				}
				// SCP-02
				if (null != scheduledPayment.getPaymentType()) {
					scheduledPayment.setPaymentStatus(PaymentStatus.PAYE);
					scheduledPayment.setCommonStatus(CommonStatus.INACTIF);
				}else {
					if(scheduledPayment.getPaymentStatus().equals(PaymentStatus.ANNULE)) {
						scheduledPayment.setCommonStatus(CommonStatus.INACTIF);
					}else {
						scheduledPayment.setCommonStatus(CommonStatus.ACTIF);
					}
				}
				
				paymentRepository.save(scheduledPayment);
				return new ResponseEntity<ScheduledPayment>(scheduledPayment, HttpStatus.OK);
			} else {
				LOGGER.error(RentalMessage.entityNotFound, scheduledPayment.getId());
				return new ResponseEntity<ScheduledPayment>(HttpStatus.NOT_FOUND);
			}
		} else {
			// Case add
			if (null != contract) {
				if (null != contract.getId()) {
					scheduledPayment.setContract(contract);
					// Add Price : get the active price
					Price price = priceRepository.findPriceByCommonStatusAndScheduledPayment("ACTIF", contract.getId())
							.get();
					scheduledPayment.setPrice(price);

					// Calculate the amount

					DurationType durationType = price.getDurationType();
					Float initialAmount = price.getAmount();
					Float amount = Utils.calculateAmountByMonth(durationType, initialAmount);

					// Get the contract info

					contract = contractRepository.findById(contract.getId()).get();
					ScheduledPaymentType enumPayType = contract.getScheduledPaymentType();
					scheduledPayment.setAmount(Utils.calculateAmountByPeriod(enumPayType, amount));
					scheduledPayment.setCurrency(Currency.€);

					// SCP-02
					if (null != scheduledPayment.getPaymentType()) {
						scheduledPayment.setPaymentStatus(PaymentStatus.PAYE);
						scheduledPayment.setCommonStatus(CommonStatus.INACTIF);
					}else {
						if(scheduledPayment.getPaymentStatus().equals(PaymentStatus.ANNULE)) {
							scheduledPayment.setCommonStatus(CommonStatus.INACTIF);
						}else {
							scheduledPayment.setCommonStatus(CommonStatus.ACTIF);
						}
					}

					paymentRepository.save(scheduledPayment);
					return new ResponseEntity<ScheduledPayment>(scheduledPayment, HttpStatus.OK);
				} else {
					LOGGER.error(RentalMessage.entityIsNull, "contrat");
					return new ResponseEntity<ScheduledPayment>(HttpStatus.NO_CONTENT);
				}
			} else {
				LOGGER.error(RentalMessage.entityIsNull, "contrat");
				return new ResponseEntity<ScheduledPayment>(HttpStatus.NO_CONTENT);
			}
		}
	}

	public ResponseEntity<List<ScheduledPayment>> getPaymentsByContractIdAndStatus(Long id, String commonStatus) {
		// TODO Auto-generated method stub
		Optional<List<ScheduledPayment>>  listSch = paymentRepository.findSchedPaymentByCommonStatusAndScheduledPayment(commonStatus, id);
		System.out.println(listSch);
		return new ResponseEntity<List<ScheduledPayment>>(listSch.get(),HttpStatus.OK);
	}
}
