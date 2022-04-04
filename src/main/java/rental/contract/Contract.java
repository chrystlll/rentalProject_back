package rental.contract;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import rental.enumeration.CommonStatus;
import rental.enumeration.ContractType;
import rental.mainTenant.MainTenant;
import rental.renter.Renter;
import rental.price.Price;

@Entity
@Table(name = "contract")
public class Contract {
	@Id
	@SequenceGenerator(name = "contract_sequence", sequenceName = "contract_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract_sequence")
	private Long id;
	private Date startDate;
	private Date endDate;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JsonBackReference
	private MainTenant mainTenant;

	@Enumerated(EnumType.STRING)
	@Column(name = "contractType")
	private ContractType contractType;

	@Enumerated(EnumType.STRING)
	@Column(name = "commonStatus")
	private CommonStatus commonStatus;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private Renter renter;
	
	
	@OneToMany(mappedBy = "contract", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Price> price;
//
//	@OneToMany(mappedBy = "contract", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private Set<Vehicle> vehicle;

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
	
	public Contract(Date startDate, Date endDate, MainTenant mainTenant, ContractType contractType, CommonStatus commonStatus) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.mainTenant = mainTenant;
		this.contractType = contractType;
		this.commonStatus = commonStatus;
	}

	public Contract(Long id, Date startDate, Date endDate, MainTenant mainTenant, ContractType contractType,
			CommonStatus commonStatus) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.mainTenant = mainTenant;
		this.contractType = contractType;
		this.commonStatus = commonStatus;
	}
	
	public Contract(Long id, Date startDate, Date endDate, MainTenant mainTenant, ContractType contractType,
			CommonStatus commonStatus, Renter renter, Set<Price> price) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.mainTenant = mainTenant;
		this.contractType = contractType;
		this.commonStatus = commonStatus;
		this.renter = renter;
		this.price = price;
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
	 * @return the price
	 */
	public Set<Price> getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Set<Price> price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Contract [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", mainTenant=" + mainTenant
				+ ", contractType=" + contractType + ", commonStatus=" + commonStatus + ", renter=" + renter
				+ ", price=" + price + "]";
	}


}
