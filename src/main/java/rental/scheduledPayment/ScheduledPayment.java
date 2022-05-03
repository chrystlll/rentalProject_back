package rental.scheduledPayment;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import rental.contract.Contract;
import rental.enumeration.CommonStatus;
import rental.enumeration.Currency;
import rental.enumeration.PaymentStatus;
import rental.enumeration.PaymentType;
import rental.price.Price;

@Entity
@Table(name = "scheduledPayment")
public class ScheduledPayment {
	@Id
	@SequenceGenerator(name = "scheduledPayment_sequence", sequenceName = "scheduledPayment_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scheduledPayment_sequence")
	private Long id;

	// Period
	private Date startDate;
	private Date endDate;

	@Enumerated(EnumType.STRING)
	private PaymentType paymentType;

	// Date of the payment
	private Date paymentDate;

	// Date when the payment is generated
	private Date scheduledPaymentGenerationDate;
	
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;

	@Enumerated(EnumType.STRING)
	private CommonStatus commonStatus;

	// Due date : Note: if dueDate < Now() => change paystatus
	private Date dueDate;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JsonBackReference(value = "contract-payment")
	private Contract contract;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JsonBackReference(value = "price-payment")
	private Price price;
	private Float amount;

	@Enumerated(EnumType.STRING)
	private Currency currency;

	public ScheduledPayment() {
		super();
	}

	public ScheduledPayment(Date startDate, PaymentType paymentType, Contract contract) {
		super();
		this.startDate = startDate;
		this.paymentType = paymentType;
		this.contract = contract;
	}
	
	

	public ScheduledPayment(Long id, Date startDate, Date endDate, PaymentType paymentType, Date paymentDate,
			Date scheduledPaymentGenerationDate, PaymentStatus paymentStatus, CommonStatus commonStatus, Date dueDate,
			Contract contract, Price price, Float amount, Currency currency) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.paymentType = paymentType;
		this.paymentDate = paymentDate;
		this.scheduledPaymentGenerationDate = scheduledPaymentGenerationDate;
		this.paymentStatus = paymentStatus;
		this.commonStatus = commonStatus;
		this.dueDate = dueDate;
		this.contract = contract;
		this.price = price;
		this.amount = amount;
		this.currency = currency;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the paymentType
	 */
	public PaymentType getPaymentType() {
		return paymentType;
	}

	/**
	 * @param paymentType the paymentType to set
	 */
	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	/**
	 * @return the paymentDate
	 */
	public Date getPaymentDate() {
		return paymentDate;
	}

	/**
	 * @param paymentDate the paymentDate to set
	 */
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	/**
	 * @return the contract
	 */
	public Contract getContract() {
		return contract;
	}

	/**
	 * @param contract the contract to set
	 */
	public void setContract(Contract contract) {
		this.contract = contract;
	}

	/**
	 * @return the price
	 */
	public Price getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Price price) {
		this.price = price;
	}

	/**
	 * @return the scheduledPaymentGenerationDate
	 */
	public Date getScheduledPaymentGenerationDate() {
		return scheduledPaymentGenerationDate;
	}

	/**
	 * @param scheduledPaymentGenerationDate the scheduledPaymentGenerationDate to
	 *                                       set
	 */
	public void setScheduledPaymentGenerationDate(Date scheduledPaymentGenerationDate) {
		this.scheduledPaymentGenerationDate = scheduledPaymentGenerationDate;
	}


	/**
	 * @return the dueDate
	 */
	public Date getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * @return the amount
	 */
	public Float getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Float amount) {
		this.amount = amount;
	}

	/**
	 * @return the currency
	 */
	public Currency getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}


	/**
	 * @return the commonStatus
	 */
	public CommonStatus getCommonStatus() {
		return commonStatus;
	}

	/**
	 * @param commonStatus the commonStatus to set
	 */
	public void setCommonStatus(CommonStatus commonStatus) {
		this.commonStatus = commonStatus;
	}


	/**
	 * @return the paymentStatus
	 */
	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	/**
	 * @param paymentStatus the paymentStatus to set
	 */
	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	
	

}
