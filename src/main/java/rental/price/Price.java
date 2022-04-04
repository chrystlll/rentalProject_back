package rental.price;

import java.util.Date;

import javax.persistence.Column;
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

@Entity
@Table(name = "price")
public class Price {

	@Id
	@SequenceGenerator(name = "price_sequence", sequenceName = "price_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "price_sequence")
	private long id;
	private Date startDate;
	private Date endDate;
	private Float amount;
	@Enumerated(EnumType.STRING)
	private Currency currency;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JsonBackReference
	private Contract contract;

	@Enumerated(EnumType.STRING)
	@Column(name = "commonStatus")
	private CommonStatus commonStatus;

	public Price() {
		super();
	}

	public Price(long id) {
		super();
		this.id = id;
	}

	public Price(Date startDate, Float amount, Currency currency, Contract contract) {
		super();
		this.startDate = startDate;
		this.amount = amount;
		this.currency = currency;
		this.contract = contract;
	}

	public Price(long id, Date startDate, Date endDate, Float amount, Currency currency, Contract contract) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.currency = currency;
		this.contract = contract;
	}

	public Price(long id, Date startDate, Date endDate, Float amount, Currency currency, Contract contract,
			CommonStatus commonStatus) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.currency = currency;
		this.contract = contract;
		this.commonStatus = commonStatus;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
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

	@Override
	public String toString() {
		return "Price [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", amount=" + amount
				+ ", currency=" + currency + ", contract=" + contract + ", commonStatus=" + commonStatus + "]";
	}

}
