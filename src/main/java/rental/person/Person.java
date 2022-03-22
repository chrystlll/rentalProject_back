package rental.person;

import java.text.ParseException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rental.enumeration.Gender;
import rental.utils.RegExpMatching;

@MappedSuperclass
public abstract class Person {

	static Logger LOGGER = LogManager.getLogger(Person.class);

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

	public Person(String firstName, Gender gender) {
		super();
		this.firstName = firstName;
		this.gender = gender;
	}

	public Person(String lastName,String firstName, Gender gender, String email) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.gender = gender;
		this.email = email;
	}
	
	

	public Person(String firstName, String lastName, Gender gender, String email,
			String phoneNumber1, String phoneNumber2) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.phoneNumber1 = phoneNumber1;
		this.phoneNumber2 = phoneNumber2;
	}

	public Person(String firstName, String lastName, Date dob, Gender gender, String email, String socialNumber) {
		super();

		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.gender = gender;
		this.email = email;
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
	 * @throws ParseException
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
	 * Cast the gender in Enumeration
	 * 
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = Gender.valueOf(gender);
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Control of email by regex
	 * 
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		if (!email.isEmpty() && !RegExpMatching.isValidEmail(email)) {
			LOGGER.error("L'email : " + email + " est invalide!");
			;
			throw new RuntimeException("Email is not valid !!!");
		} else {
			this.email = email;
		}
	}

	/**
	 * @return the socialNumber
	 */
	public String getSocialNumber() {
		return socialNumber;
	}

	/**
	 * Control of social number (max 15 char)
	 * 
	 * @param socialNumber the socialNumber to set
	 */
	public void setSocialNumber(String socialNumber) {
		if (15 != socialNumber.length() && 0 != socialNumber.length()) {
			throw new RuntimeException("Social number is invalid !!! Length error");
		} else {
			this.socialNumber = socialNumber;
		}
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
		if (null != phoneNumber1) {
			if (10 != phoneNumber1.length() && 0 != phoneNumber1.length()) {
				throw new RuntimeException("Phone number is invalid !!! Length error");
			} else {
				this.phoneNumber1 = phoneNumber1;
			}
		}
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
		if (null != phoneNumber2) {
			if (10 != phoneNumber2.length() && 0 != phoneNumber2.length()) {
				throw new RuntimeException("Phone number is invalid !!! Length error");
			} else {
				this.phoneNumber2 = phoneNumber2;
			}
		}
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob + ", gender="
				+ gender + ", email=" + email + ", socialNumber=" + socialNumber + ", phoneNumber1=" + phoneNumber1
				+ ", phoneNumber2=" + phoneNumber2 + "]";
	}

}
