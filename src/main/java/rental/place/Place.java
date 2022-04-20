
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
@Table
public class Place {

	@Id
	@SequenceGenerator(name = "property_sequence", sequenceName = "property_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "property_sequence")
	private Long id;
	private String name;
	@Enumerated(EnumType.STRING)
	private CommonStatus commonStatus;
	private Float locationtSize;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private Address address;

//	@OneToOne(fetch = FetchType.LAZY, optional = true)
//	private Contract contract;

	public Place(String name, CommonStatus commonStatus) {
		super();
		this.name = name;
		this.commonStatus = commonStatus;
	}

	public Place(Long id, String name, CommonStatus commonStatus, Float locationtSize, Address address) {
		super();
		this.id = id;
		this.name = name;
		this.commonStatus = commonStatus;
		this.locationtSize = locationtSize;
		this.address = address;
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

//	/**
//	 * @return the contract
//	 */
//	public Contract getContract() {
//		return contract;
//	}
//
//	/**
//	 * @param contract the contract to set
//	 */
//	public void setContract(Contract contract) {
//		this.contract = contract;
//	}
//
//	@Override
//	public String toString() {
//		return "Place [id=" + id + ", name=" + name + ", commonStatus=" + commonStatus + ", locationtSize="
//				+ locationtSize + ", address=" + address + ", contract=" + contract + "]";
//	}

}
