package rental.contract;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
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

import rental.enumeration.ContractType;
import rental.mainTenant.MainTenant;
import rental.price.Price;
import rental.renter.Renter;
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
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	private MainTenant mainTenant;
	@Enumerated(EnumType.STRING)
	private ContractType contractType;
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private Renter renter;
	@OneToMany(mappedBy = "contract", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Price> price;

	@OneToMany(mappedBy = "contract", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Vehicle> vehicle;
	
	public Contract(Long id, Date startDate, Date endDate) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Contract(Date startDate, Date endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
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

	/**
	 * @return the contractType
	 */
	public Enum<ContractType> getContractType() {
		return contractType;
	}

	/**
	 * @param contractType the contractType to set
	 */
	public void setContractType(ContractType contractType) {
		this.contractType = contractType;
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
				+ ", contractType=" + contractType + ", renter=" + renter + ", price=" + price + "]";
	}

}
