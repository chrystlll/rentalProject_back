package rental.contract;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import rental.enumeration.CommonStatus;
import rental.enumeration.ContractType;
import rental.enumeration.DurationType;
import rental.enumeration.ScheduledPaymentType;
import rental.mainTenant.MainTenant;
import rental.place.Place;
import rental.renter.Renter;
import rental.scheduledPayment.ScheduledPayment;
import rental.vehicle.Vehicle;

@Entity
@Table(name = "contract")
public class Contract {
	@Id
	@SequenceGenerator(name = "contract_sequence", sequenceName = "contract_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract_sequence")
	private Long id;
	private Date startDate;
	private Date endDate;
	@Enumerated(EnumType.STRING)
	@Column(name = "contractType")
	private ContractType contractType;

	@Enumerated(EnumType.STRING)
	@Column(name = "initialDurationType")
	private DurationType initialDurationType;

	private float initialAmount;

	@Enumerated(EnumType.STRING)
	@Column(name = "commonStatus")
	private CommonStatus commonStatus;

	@Enumerated(EnumType.STRING)
	@Column(name = "scheduledPaymentType")
	private ScheduledPaymentType scheduledPaymentType;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private MainTenant mainTenant;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private Renter renter;

	@OneToMany(mappedBy = "contract", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ScheduledPayment> payment;

	@OneToMany(mappedBy = "contract", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Vehicle> vehicle;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Place place;

	public Contract() {
		super();
	}

	public Contract(MainTenant mainTenant, ContractType contractType, CommonStatus cs) {
		super();
		this.mainTenant = mainTenant;
		this.contractType = contractType;
		this.commonStatus = cs;
	}

	public Contract(Date startDate, Date endDate, MainTenant mainTenant, ContractType contractType) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.mainTenant = mainTenant;
		this.contractType = contractType;
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
	 * @return the mainTenant
	 */
	public MainTenant getMainTenant() {
		return mainTenant;
	}

	/**
	 * @param mainTenant the mainTenant to set
	 */
	public void setMainTenant(MainTenant mainTenant) {
		this.mainTenant = mainTenant;
	}

	/**
	 * @return the contractType
	 */
	public ContractType getContractType() {
		return contractType;
	}

	/**
	 * @param contractType the contractType to set
	 */
	public void setContractType(ContractType contractType) {
		this.contractType = contractType;
	}

	/**
	 * @return the renter
	 */
	public Renter getRenter() {
		return renter;
	}

	/**
	 * @param renter the renter to set
	 */
	public void setRenter(Renter renter) {
		this.renter = renter;
	}

	/**
	 * @return the payment
	 */
	public List<ScheduledPayment> getPayment() {
		return payment;
	}

	/**
	 * @param payment the payment to set
	 */
	public void setPayment(List<ScheduledPayment> payment) {
		this.payment = payment;
	}

	/**
	 * @return the vehicle
	 */
	public List<Vehicle> getVehicle() {
		return vehicle;
	}

	/**
	 * @param vehicle the vehicle to set
	 */
	public void setVehicle(List<Vehicle> vehicle) {
		this.vehicle = vehicle;
	}

	/**
	 * @return the scheduledPaymentType
	 */
	public ScheduledPaymentType getScheduledPaymentType() {
		return scheduledPaymentType;
	}

	/**
	 * @param scheduledPaymentType the scheduledPaymentType to set
	 */
	public void setScheduledPaymentType(ScheduledPaymentType scheduledPaymentType) {
		this.scheduledPaymentType = scheduledPaymentType;
	}

	/**
	 * @return the initialDurationType
	 */
	public DurationType getInitialDurationType() {
		return initialDurationType;
	}

	/**
	 * @param initialDurationType the initialDurationType to set
	 */
	public void setInitialDurationType(DurationType initialDurationType) {
		this.initialDurationType = initialDurationType;
	}

	/**
	 * @return the initialAmount
	 */
	public float getInitialAmount() {
		return initialAmount;
	}

	/**
	 * @param initialAmount the initialAmount to set
	 */
	public void setInitialAmount(float initialAmount) {
		this.initialAmount = initialAmount;
	}

	/**
	 * @return the place
	 */
	public Place getPlace() {
		return place;
	}

	/**
	 * @param place the place to set
	 */
	public void setPlace(Place place) {
		this.place = place;
	}

	@Override
	public String toString() {
		return "Contract [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", contractType="
				+ contractType + ", initialDurationType=" + initialDurationType + ", initialAmount=" + initialAmount
				+ ", commonStatus=" + commonStatus + ", scheduledPaymentType=" + scheduledPaymentType + ", mainTenant="
				+ mainTenant + ", renter=" + renter + ", payment=" + payment + ", vehicle=" + vehicle + ", place="
				+ place + "]";
	}

}
