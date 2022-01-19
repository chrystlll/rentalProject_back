
package rental.property;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import rental.enumeration.PropertyStatus;

@Entity
@Table
public class Property {

	@Id
	@SequenceGenerator(name = "property_sequence", sequenceName = "property_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "property_sequence")
	private Long id;
	private String name;
	private Enum<PropertyStatus> propertyStatus;

	public Property(String name, Enum<PropertyStatus> propertyStatus) {
		super();
		this.name = name;
		this.propertyStatus = propertyStatus;
	}
	
	

	public Property(String name) {
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
	public void setPropertyStatus(Enum<PropertyStatus> propertyStatus) {
		this.propertyStatus = propertyStatus;
	}

	@Override
	public String toString() {
		return "Property [id=" + id + ", name=" + name + ", propertyStatus=" + propertyStatus + "]";
	}

}
