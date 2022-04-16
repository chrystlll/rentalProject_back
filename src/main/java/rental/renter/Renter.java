package rental.renter;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import rental.address.Address;
import rental.contract.Contract;
import rental.person.Person;

@Entity
@Table(name = "renter")
public class Renter extends Person {

	@JsonBackReference(value="renter-address")
	@OneToOne(mappedBy = "renter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Address address;

	@JsonBackReference(value="renter-contract")
	@OneToMany(mappedBy = "renter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Contract> contracts;

	public Renter() {
		super();
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
	 * @return the contracts
	 */
	public List<Contract> getContracts() {
		return contracts;
	}

	/**
	 * @param contracts the contracts to set
	 */
	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}

	@Override
	public String toString() {
		return "Renter [address=" + address + ", contracts=" + contracts + "]";
	}

}
