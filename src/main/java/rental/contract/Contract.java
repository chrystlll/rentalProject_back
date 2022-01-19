package rental.contract;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import rental.enumeration.ContractType;
import rental.mainTenant.MainTenant;
import rental.price.Price;
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
	private Boolean isExist;
	private Enum<ContractType> contractType;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	private MainTenant mainTenant;
	@OneToMany(mappedBy = "contract", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Vehicle> vehicle;
	private Float locationtSize;
	@OneToMany(mappedBy = "contract", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Price> price;

	public Contract(Long id, Date startDate, Date endDate, Boolean isExist) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.isExist = isExist;
	}

	public Contract(Date startDate, Date endDate, Boolean isExist) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.isExist = isExist;
	}

	public Long getId() {
		return id;
	}

	/**
	 * @return the maintenant
	 */
	public MainTenant getMaintenant() {
		return mainTenant;
	}

	/**
	 * @param maintenant the maintenant to set
	 */
	public void setMaintenant(MainTenant maintenant) {
		this.mainTenant = maintenant;
	}

	public void setId(Long id) {
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

	public Boolean getIsExist() {
		return isExist;
	}

	public void setIsExist(Boolean isExist) {
		this.isExist = isExist;
	}

	/**
	 * @return the contractType
	 */
	public Enum<ContractType> getContractType() {
		return contractType;
	}

	/**
	 * @param contractType the contractType to set
	 */
	public void setContractType(Enum<ContractType> contractType) {
		this.contractType = contractType;
	}

	/**
	 * @return the vehicle
	 */
	public Set<Vehicle> getVehicle() {
		return vehicle;
	}

	/**
	 * @param vehicle the vehicle to set
	 */
	public void setVehicle(Set<Vehicle> vehicle) {
		this.vehicle = vehicle;
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

	/**
	 * @return the locationtSize
	 */
	public Float getLocationtSize() {
		return locationtSize;
	}

	/**
	 * @param locationtSize the locationtSize to set
	 */
	public void setLocationtSize(Float locationtSize) {
		this.locationtSize = locationtSize;
	}

	@Override
	public String toString() {
		return "Contract [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", isExist=" + isExist
				+ "]";
	}

}
