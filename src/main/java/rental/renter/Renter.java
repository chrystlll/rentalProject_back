package rental.renter;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import rental.address.Address;
import rental.contract.Contract;
import rental.enumeration.Gender;
import rental.person.Person;

@Entity
@Table(name = "renter")
public class Renter extends Person {

	@OneToOne(mappedBy = "renter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Address address;
	
//	@OneToMany(mappedBy = "renter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private List<Contract> contracts;

	public Renter() {
		super();
	}

	public Renter(String firstname, Gender gender) {
		super(firstname, gender);
	}
	
	public Renter(String firstName, String lastName, Gender gender, String email,String phoneNumber1,
	String phoneNumber2) {
		super(firstName,lastName,gender,email,phoneNumber1,phoneNumber2);
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

	@Override
	public String toString() {
		return "Renter [address=" + address + "]";
	}


}
