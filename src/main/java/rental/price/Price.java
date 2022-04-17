package rental.price;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import rental.enumeration.CommonStatus;
import rental.enumeration.Currency;
import rental.enumeration.DurationType;
import rental.scheduledPayment.ScheduledPayment;

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

	@Enumerated(EnumType.STRING)
	@Column(name = "commonStatus")
	private CommonStatus commonStatus;

	@Enumerated(EnumType.STRING)
	@Column(name = "durationType")
	private DurationType durationType;


	@OneToMany(mappedBy = "price", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ScheduledPayment> listScheduled;

	public Price() {
		super();
	}

	public Price(long id) {
		super();
		this.id = id;
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
	 * @return the durationType
	 */
	public DurationType getDurationType() {
		return durationType;
	}

	/**
	 * @param durationType the durationType to set
	 */
	public void setDurationType(DurationType durationType) {
		this.durationType = durationType;
	}

	

	/**
	 * @return the listScheduled
	 */
	public Set<ScheduledPayment> getListScheduled() {
		return listScheduled;
	}

	/**
	 * @param listScheduled the listScheduled to set
	 */
	public void setListScheduled(Set<ScheduledPayment> listScheduled) {
		this.listScheduled = listScheduled;
	}

	@Override
	public String toString() {
		return "Price [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", amount=" + amount
				+ ", currency=" + currency + ", commonStatus=" + commonStatus + ", durationType=" + durationType
				+ ", listScheduled=" + listScheduled + "]";
	}

}
