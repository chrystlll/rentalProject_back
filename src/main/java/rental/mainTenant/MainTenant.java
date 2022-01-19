package rental.mainTenant;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import rental.address.Address;
import rental.contract.Contract;
import rental.enumeration.Gender;
import rental.enumeration.TenantStatus;

@Entity
@Table (name ="mainTenant")
public class MainTenant {

	@Id
	@SequenceGenerator(name = "mainTenant_sequence", sequenceName = "mainTenant_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mainTenant_sequence")
	private long id;
	private String firstName;
	private String surname;
	private Date dob;
	private Enum<Gender> gender;
	private String phoneNumber;
	private String email;
	private Enum<TenantStatus> mainTenantStatus;
	private String socialNumber;
	@OneToMany(mappedBy = "mainTenant", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
	private List<Address> addresses;
	@OneToOne(mappedBy = "mainTenant", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
	private Contract contract;

	
	public MainTenant(String firstName) {
		super();
		this.firstName = firstName;
	}

	
	public MainTenant() {
		super();
	}


	public MainTenant(String firstName, String surname, Date dob, Enum<Gender> gender, String phoneNumber, String email,
			Enum<TenantStatus> mainTenantStatus, String socialNumber) {
		super();
		this.firstName = firstName;
		this.surname = surname;
		this.dob = dob;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.mainTenantStatus = mainTenantStatus;
		this.socialNumber = socialNumber;
	}

	
	public MainTenant(long id, String firstName, String surname, Date dob, Enum<Gender> gender, String phoneNumber,
			String email, Enum<TenantStatus> mainTenantStatus, String socialNumber, List<Address> addresses,
			Contract contract) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.surname = surname;
		this.dob = dob;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.mainTenantStatus = mainTenantStatus;
		this.socialNumber = socialNumber;
		this.addresses = addresses;
		this.contract = contract;
	}

	public MainTenant(long id, String firstName, String surname, Date dob, Enum<Gender> gender, String phoneNumber,
			String email, Enum<TenantStatus> mainTenantStatus, String socialNumber) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.surname = surname;
		this.dob = dob;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.mainTenantStatus = mainTenantStatus;
		this.socialNumber = socialNumber;
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
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
	 * @return the mainTenantStatus
	 */
	public Enum<TenantStatus> getMainTenantStatus() {
		return mainTenantStatus;
	}

	/**
	 * @param mainTenantStatus the mainTenantStatus to set
	 */
	public void setMainTenantStatus(Enum<TenantStatus> mainTenantStatus) {
		this.mainTenantStatus = mainTenantStatus;
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
		return "MainTenant [id=" + id + ", firstName=" + firstName + ", surname=" + surname + ", dob=" + dob
				+ ", gender=" + gender + ", phoneNumber=" + phoneNumber + ", email=" + email + ", mainTenantStatus="
				+ mainTenantStatus + ", socialNumber=" + socialNumber + ", addresses=" + addresses + ", contract="
				+ contract + "]";
	}
	
	

	

}
