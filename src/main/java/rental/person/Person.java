package rental.person;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import rental.enumeration.Gender;
import rental.logger.LOGG;
import rental.utils.RegExpMatching;
import rental.utils.RentalMessage;

@MappedSuperclass
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@AttributeOverride(name = "id", column = @Column(name = "ID"))
public abstract class Person {

	static Logger LOGGER = LOGG.getLogger(Person.class);

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_sequence")
	@SequenceGenerator(name = "person_sequence", sequenceName = "person_sequence", allocationSize = 1)
	private long id;
	private String firstName;
	private String lastName;

	@Column(length = 10)
	private Date dob;

	@Enumerated(EnumType.STRING)
	@Column(name = "gender")
	private Gender gender;

	private String email;
	@Column(length = 15)
	private String socialNumber;
	private String phoneNumber1;
	private String phoneNumber2;

	public Person() {
		super();
	}
	
	

	public Person(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}



	public Person(long id, String firstName, String lastName, Date dob, Gender gender, String email,
			String socialNumber, String phoneNumber1, String phoneNumber2) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.gender = gender;
		this.email = email;
		this.socialNumber = socialNumber;
		this.phoneNumber1 = phoneNumber1;
		this.phoneNumber2 = phoneNumber2;
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
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public Gender getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the socialNumber
	 */
	public String getSocialNumber() {
		return socialNumber;
	}

	/**
	 * @return the phoneNumber1
	 */
	public String getPhoneNumber1() {
		return phoneNumber1;
	}

	/**
	 * @return the phoneNumber2
	 */
	public String getPhoneNumber2() {
		return phoneNumber2;
	}

	/**
	 * Control of email by regex
	 * 
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		if (!email.isEmpty() && !RegExpMatching.isValidEmail(email)) {
			LOGGER.error(RentalMessage.emailInvalid,email);
			throw new RuntimeException(RentalMessage.emailInvalid);
		} else {
			this.email = email;
		}
	}

	/**
	 * Control of social number (max 15 char)
	 * 
	 * @param socialNumber the socialNumber to set
	 */
	public void setSocialNumber(String socialNumber) {
		if (15 != socialNumber.length() && 0 != socialNumber.length()) {
			LOGGER.error(RentalMessage.socialNumberInvalid,socialNumber,socialNumber.length());
			throw new RuntimeException(RentalMessage.socialNumberInvalid);
		} else {
			this.socialNumber = socialNumber;
		}
	}

	/**
	 * @param phoneNumber1 the phoneNumber1 to set
	 */
	public void setPhoneNumber1(String phoneNumber1) {
		if (null != phoneNumber1) {
			if (10 != phoneNumber1.length() && 0 != phoneNumber1.length()) {
				LOGGER.error(RentalMessage.phoneNumberInvalid,phoneNumber1,phoneNumber1.length());
				throw new RuntimeException(RentalMessage.phoneNumberInvalid);
			} else {
				this.phoneNumber1 = phoneNumber1;
			}
		}
	}

	/**
	 * @param phoneNumber2 the phoneNumber2 to set
	 */
	public void setPhoneNumber2(String phoneNumber2) {
		if (null != phoneNumber2) {
			if (10 != phoneNumber2.length() && 0 != phoneNumber2.length()) {
				throw new RuntimeException("Phone number is invalid !!! Length error");
			} else {
				this.phoneNumber2 = phoneNumber2;
			}
		}
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob + ", gender="
				+ gender + ", email=" + email + ", socialNumber=" + socialNumber + ", phoneNumber1=" + phoneNumber1
				+ ", phoneNumber2=" + phoneNumber2 + "]";
	}
	
	

}
