package rental.vehicle;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import rental.contract.Contract;

@Entity
@Table (name = "vehicle")
public class Vehicle {

	@Id
	@SequenceGenerator(name = "vehicle_sequence", sequenceName = "vehicle_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicle_sequence")
	private Long id;
	private String registrationNumber;
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Contract contract;
	

	public Vehicle(String registrationNumber, String name) {
		super();
		this.registrationNumber = registrationNumber;
		this.name = name;
	}
	
	

	public Vehicle(String name) {
		super();
		this.name = name;
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
	 * @return the registrationNumber
	 */
	public String getRegistrationNumber() {
		return registrationNumber;
	}

	/**
	 * @param registrationNumber the registrationNumber to set
	 */
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
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

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", registrationNumber=" + registrationNumber + ", name=" + name + "]";
	}

}
