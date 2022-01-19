package rental.price;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import rental.contract.Contract;
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
	private Enum<Currency> currency;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Contract contract;

	public Price(long id, Date startDate, Date endDate, Float amount, Enum<Currency> currency, Contract contract) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.currency = currency;
		this.contract = contract;
	}

	public Price(Date startDate, Date endDate, Float amount, Enum<Currency> currency, Contract contract) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.currency = currency;
		this.contract = contract;
	}

	public Price(long id) {
		super();
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Enum<Currency> getCurrency() {
		return currency;
	}

	public void setCurrency(Enum<Currency> currency) {
		this.currency = currency;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	@Override
	public String toString() {
		return "Price [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", amount=" + amount
				+ ", currency=" + currency + ", contract=" + contract + "]";
	}

}
