
package rental.place;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import rental.address.Address;
import rental.contract.Contract;
import rental.enumeration.CommonStatus;

@Entity
@Table(name = "place")
public class Place {

	@Id
	@SequenceGenerator(name = "place_sequence", sequenceName = "place_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "place_sequence")
	private Long id;
	private String name;
	@Enumerated(EnumType.STRING)
	private CommonStatus commonStatus;
	private Float locationSize;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private Address address;

	@OneToOne(fetch = FetchType.LAZY, optional = true)
	private Contract contract;
	
	

	public Place() {
		super();
	}

	public Place(String name, CommonStatus commonStatus) {
		super();
		this.name = name;
		this.commonStatus = commonStatus;
	}

	public Place(Long id, String name, CommonStatus commonStatus, Float locationSize, Address address) {
		super();
		this.id = id;
		this.name = name;
		this.commonStatus = commonStatus;
		this.locationSize = locationSize;
		this.address = address;
	}

	/**
	 * @return the locationSize
	 */
	public Float getLocationtSize() {
		return locationSize;
	}

	/**
	 * @param locationSize the locationSize to set
	 */
	public void setLocationtSize(Float locationSize) {
		this.locationSize = locationSize;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	public Place(String name) {
		super();
		this.name = name;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the commonStatus
	 */
	public Enum<CommonStatus> getCommonStatus() {
		return commonStatus;
	}

	/**
	 * @param propertyStatus the propertyStatus to set
	 */
	public void setCommonStatus(CommonStatus commonStatus) {
		this.commonStatus = commonStatus;
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

	@Override
	public String toString() {
		return "Place [id=" + id + ", name=" + name + ", commonStatus=" + commonStatus + ", locationSize="
				+ locationSize + ", address=" + address + ", contract=" + contract + "]";
	}

}
