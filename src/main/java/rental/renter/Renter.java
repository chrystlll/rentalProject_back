package rental.renter;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import rental.address.Address;
import rental.enumeration.Gender;

@Entity
@Table (name ="renter")
public class Renter {

	@Id
	@SequenceGenerator(name = "renter_sequence", sequenceName = "renter_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "renter_sequence")
	private long id;
	private String firstName;
	private String surname;
	private Date dob;
	private Enum<Gender> gender;
	private String phoneNumber1;
	private String phoneNumber2;
	private String email;
	private String socialNumber;
	
	@OneToMany(mappedBy = "renter", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
	private List<Address> addresses;

	public Renter(String firstName) {
		super();
		this.firstName = firstName;
	}
	
	public Renter(String firstName, String surname, Date dob, Enum<Gender> gender, String phoneNumber1,
			String phoneNumber2, String email, String socialNumber, Address address) {
		super();
		this.firstName = firstName;
		this.surname = surname;
		this.dob = dob;
		this.gender = gender;
		this.phoneNumber1 = phoneNumber1;
		this.phoneNumber2 = phoneNumber2;
		this.email = email;
		this.socialNumber = socialNumber;
	}
	
	
	public Renter() {
		super();
	}

	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}
	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	/**
	 * @return the dob
	 */
	public Date getDob() {
		return dob;
	}
	/**
	 * @param dob the dob to set
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}
	/**
	 * @return the gender
	 */
	public Enum<Gender> getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(Enum<Gender> gender) {
		this.gender = gender;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the socialNumber
	 */
	public String getSocialNumber() {
		return socialNumber;
	}
	/**
	 * @param socialNumber the socialNumber to set
	 */
	public void setSocialNumber(String socialNumber) {
		this.socialNumber = socialNumber;
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
		return "Renter [id=" + id + ", firstName=" + firstName + ", surname=" + surname + ", dob=" + dob + ", gender="
				+ gender + ", phoneNumber1=" + phoneNumber1 + ", phoneNumber2=" + phoneNumber2 + ", email=" + email
				+ ", socialNumber=" + socialNumber + ", addresses=" + addresses + "]";
	}
	
	
	





}
