package rental.contract;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import rental.enumeration.CommonStatus;
import rental.enumeration.Currency;
import rental.enumeration.DurationType;
import rental.enumeration.ScheduledPaymentType;
import rental.logger.LOGG;
import rental.mainTenant.MainTenant;
import rental.mainTenant.MainTenantRepository;
import rental.price.Price;
import rental.price.PriceRepository;
import rental.scheduledPayment.ScheduledPayment;
import rental.scheduledPayment.ScheduledPaymentRepository;
import rental.utils.JpaCriteriaApiUtils;
import rental.utils.RentalMessage;
import rental.utils.Utils;

@Service
public class ContractService {
	@PersistenceContext
	EntityManager em;

	private final ContractRepository contractRepository;
	private final MainTenantRepository mainTenantRepository;
	private final ScheduledPaymentRepository scheduledPayRepository;
	private final PriceRepository priceRepository;

	static Logger LOGGER = LOGG.getLogger(ContractService.class);

	@Autowired
	public ContractService(ContractRepository contractRepository, MainTenantRepository mainTenantRepository,
			ScheduledPaymentRepository scheduledPayRepository, PriceRepository priceRepository) {
		super();
		this.contractRepository = contractRepository;
		this.mainTenantRepository = mainTenantRepository;
		this.scheduledPayRepository = scheduledPayRepository;
		this.priceRepository = priceRepository;
	}

	/**
	 * Get the list of contracts
	 * 
	 * @param: no param
	 * @return: List<Contract>
	 */
	public List<Contract> getContract() {
		return contractRepository.findAll();
	}

	/**
	 * Add and save the contract
	 * 
	 * @param: Contract contract, MainTenant mainTenant (contains only the id)
	 * @return: ResponseEntity<Contract>
	 * @implSpec: CO-01 and CO-02
	 */
	public ResponseEntity<Contract> saveContractAndLinkToMT(Contract contract, MainTenant mainTenant) {

		/** Create contract case */
		MainTenant mTenant = mainTenantRepository.findById(mainTenant.getId()).get();
		contract.setMainTenant(mTenant);
		Contract contrSave = contractRepository.save(contract);
		if (null == contrSave) {
			LOGGER.error(RentalMessage.entityNotSaved, contract.getId());
			return new ResponseEntity<Contract>(HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			// Create SchedulePayment (cf CO-01)
			
			ScheduledPayment scheduPay = new ScheduledPayment();
			scheduPay.setStartDate(contract.getStartDate());
			scheduPay.setDueDate(contract.getStartDate());
			scheduPay.setScheduledPaymentGenerationDate(contract.getStartDate());
			
			// calculate amount
			DurationType durationType = contract.getInitialDurationType();
			Float initialAmount = contract.getInitialAmount();
			Float amount = Utils.calculateAmountByMonth(durationType, initialAmount);
			ScheduledPaymentType enumPayType = contract.getScheduledPaymentType();			
			scheduPay.setAmount(Utils.calculateAmountByPeriod(enumPayType, amount));
			Date startDate = contract.getStartDate();
			scheduPay.setEndDate(Utils.calculateEndDate(startDate, enumPayType));
			
			// Create Price
			Price price = new Price();
			price.setAmount(contract.getInitialAmount());
			price.setCurrency(Currency.€);
			price.setDurationType(contract.getInitialDurationType());
			price.setStartDate(contract.getStartDate());
			price.setCommonStatus(CommonStatus.ACTIF);
			priceRepository.save(price);
			
			scheduPay.setPrice(price);
			scheduPay.setContract(contract);
			scheduPay.setCurrency(Currency.€);
			
			// Create scheduledPayment
			ScheduledPayment schedPay = scheduledPayRepository.save(scheduPay);
			
			List<ScheduledPayment> listSch = new ArrayList<ScheduledPayment>();
			listSch.add(schedPay);
			contract.setPayment(listSch);
			contractRepository.save(contract);
			LOGGER.info(RentalMessage.entitySaved, contract.getId());
			return new ResponseEntity<Contract>(contract, HttpStatus.OK);
		}

	}

	/**
	 * Save the modification of the contract
	 * 
	 * @param: Contract contract, MainTenant mainTenant (contains only the id)
	 * @return: ResponseEntity<Contract>
	 */
	public ResponseEntity<Contract> updateContractAndLinkToMT(Contract contract, MainTenant mainTenant) {

		/** Update contract case */
		Optional<Contract> contractById = contractRepository.findById(contract.getId());
		if (!contractById.isPresent()) {
			// Contract doesn't exist
			LOGGER.error(RentalMessage.entityNotFound, contract.getId());
			return new ResponseEntity<Contract>(HttpStatus.NOT_FOUND);
		} else {
			// Contract still exists
			Contract contr = contractRepository.findById(contract.getId()).get();
			contract.setPayment(contr.getPayment());
			contract.setMainTenant(contr.getMainTenant());
			contract.setRenter(contr.getRenter());
			contract.setVehicle(contr.getVehicle());
			if (null != mainTenant) {
				MainTenant mTenant = mainTenantRepository.findById(mainTenant.getId()).get();
				contract.setMainTenant(mTenant);
			}
			Contract contrSave = contractRepository.save(contract);
			if (null == contrSave) {
				LOGGER.error(RentalMessage.entityNotSaved, contract.getId());
				return new ResponseEntity<Contract>(HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				LOGGER.info(RentalMessage.entitySaved, contract.getId());
				return new ResponseEntity<Contract>(contract, HttpStatus.OK);
			}
		}
	}

	/**
	 * Delete the contract
	 * 
	 * @param: id (Long)
	 * @return ResponseEntity<Contract>
	 */
	public ResponseEntity<Contract> deleteContractById(Long id) {
		// TODO Auto-generated method stub
		Boolean searchResult = contractRepository.existsById(id);
		if (searchResult) {
			Contract contract = new Contract();
			contract = contractRepository.findById(id).get();
			contractRepository.deleteById(id);
			return new ResponseEntity<Contract>(contract, HttpStatus.OK);

		} else {
			LOGGER.error(RentalMessage.entityNotFound, id);
			return new ResponseEntity<Contract>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Get the list of contract by criteria
	 * 
	 * @param: value     string,
	 * @param: criteria, String
	 * @param: String    subCriteria
	 * @return ResponseEntity<Contract>
	 */
	public ResponseEntity<List<Contract>> getContractsByCriteria(Object value, String criteria, String subCriteria) {
		@SuppressWarnings("unchecked")
		List<Contract> listContr = (List<Contract>) JpaCriteriaApiUtils
				.getObjectByCriteria(em, value, criteria, subCriteria, Contract.class).getBody();
		return new ResponseEntity<List<Contract>>(listContr, HttpStatus.OK);
	}

	/**
	 * Get the contract by id
	 * 
	 * @param: id long
	 * @return ResponseEntity<Contract>
	 */
	public ResponseEntity<Contract> getContractById(Long id) {
		Boolean isExist = contractRepository.existsById(id);
		if (isExist) {
			Contract contr = new Contract();
			contr = contractRepository.findById(id).get();
			LOGGER.info(RentalMessage.entityExist, id);
			return new ResponseEntity<Contract>(contr, HttpStatus.OK);
		} else {
			LOGGER.error(RentalMessage.entityNotFound, id);
			return new ResponseEntity<Contract>(HttpStatus.NOT_FOUND);
		}
	}
}
