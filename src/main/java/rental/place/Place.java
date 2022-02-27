
package rental.place;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import rental.address.Address;
import rental.enumeration.PropertyStatus;
import rental.vehicle.Vehicle;

@Entity
@Table
public class Place {

	@Id
	@SequenceGenerator(name = "property_sequence", sequenceName = "property_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "property_sequence")
	private Long id;
	private String name;
	@Enumerated(EnumType.STRING)
	private PropertyStatus propertyStatus;
	private Float locationtSize;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private Address address;

	@OneToMany(mappedBy = "place", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Vehicle> vehicle;

	public Place(String name, PropertyStatus propertyStatus) {
		super();
		this.name = name;
		this.propertyStatus = propertyStatus;
	}

	public Place(Long id, String name, PropertyStatus propertyStatus, Float locationtSize, Address address,
			Set<Vehicle> vehicle) {
		super();
		this.id = id;
		this.name = name;
		this.propertyStatus = propertyStatus;
		this.locationtSize = locationtSize;
		this.address = address;
		this.vehicle = vehicle;
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
	 * @return the propertyStatus
	 */
	public Enum<PropertyStatus> getPropertyStatus() {
		return propertyStatus;
	}

	/**
	 * @param propertyStatus the propertyStatus to set
	 */
	public void setPropertyStatus(PropertyStatus propertyStatus) {
		this.propertyStatus = propertyStatus;
	}

	@Override
	public String toString() {
		return "Place [id=" + id + ", name=" + name + ", propertyStatus=" + propertyStatus + ", locationtSize="
				+ locationtSize + ", address=" + address + ", vehicle=" + vehicle + "]";
	}

}
