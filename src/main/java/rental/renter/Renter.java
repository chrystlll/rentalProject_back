package rental.renter;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import rental.address.Address;
import rental.enumeration.Gender;
import rental.person.Person;

@Entity
@Table(name = "renter")
public class Renter extends Person {

	private String phoneNumber1;
	private String phoneNumber2;

	@OneToMany(mappedBy = "renter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Address> addresses;

	public Renter() {
		super();
	}

	public Renter(String firstname, Gender gender) {
		super(firstname,gender);
	}
	
	public Renter(String phoneNumber1, String phoneNumber2, List<Address> addresses) {
		super();
		this.phoneNumber1 = phoneNumber1;
		this.phoneNumber2 = phoneNumber2;
		this.addresses = addresses;
	}

	

	/**
	 * @return the phoneNumber1
	 */
	public String getPhoneNumber1() {
		return phoneNumber1;
	}

	/**
	 * @param phoneNumber1 the phoneNumber1 to set
	 */
	public void setPhoneNumber1(String phoneNumber1) {
		this.phoneNumber1 = phoneNumber1;
	}

	/**
	 * @return the phoneNumber2
	 */
	public String getPhoneNumber2() {
		return phoneNumber2;
	}

	/**
	 * @param phoneNumber2 the phoneNumber2 to set
	 */
	public void setPhoneNumber2(String phoneNumber2) {
		this.phoneNumber2 = phoneNumber2;
	}

	/**
	 * @return the addresses
	 */
	public List<Address> getAddresses() {
		return addresses;
	}

	/**
	 * @param addresses the addresses to set
	 */
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	@Override
	public String toString() {
		return "Renter [phoneNumber1=" + phoneNumber1 + ", phoneNumber2=" + phoneNumber2 + ", addresses="
				+ addresses + "]";
	}

}
