package rental.address;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import rental.enumeration.Country;
import rental.mainTenant.MainTenant;
import rental.renter.Renter;

@Entity
@Table
public class Address {
	@Id
	@SequenceGenerator(name = "address_sequence", sequenceName = "address_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_sequence")
	private Long id;
	private String address1;
	private String address2;
	private String zipCode;
	private String city;
	private Enum<Country> country;
	private Boolean isPrimary;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Renter renter;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private MainTenant mainTenant;

	
	
	public Address() {
		super();
	}

	public Address(Long id, String address1, String address2, String zipCode, String city, Enum<Country> country,
			Boolean isPrimary) {
		super();
		this.id = id;
		this.address1 = address1;
		this.address2 = address2;
		this.zipCode = zipCode;
		this.city = city;
		this.country = country;
		this.isPrimary = isPrimary;
	}

	public Address(String address1, Enum<Country> country, Boolean isPrimary) {
		super();
		this.address1 = address1;
		this.country = country;
		this.isPrimary = isPrimary;
	}
	
	public Address(String address1, Enum<Country> country, Boolean isPrimary, MainTenant mainTenant, Renter renter) {
		super();
		this.address1 = address1;
		this.country = country;
		this.isPrimary = isPrimary;
		this.mainTenant = mainTenant;
		this.renter = renter;
	}
	
	

	public Address(Long id, String address1) {
		super();
		this.id = id;
		this.address1 = address1;
	}

	/**
	 * @return the mainTenant
	 */
	@JsonIgnore
	public MainTenant getMainTenant() {
		return mainTenant;
	}

	/**
	 * @param mainTenant the mainTenant to set
	 */
	public void setMainTenant(MainTenant mainTenant) {
		this.mainTenant = mainTenant;
	}

	/**
	 * @return the renter
	 */
	@JsonIgnore
	public Renter getRenter() {
		return renter;
	}

	/**
	 * @param renter the renter to set
	 */
	public void setRenter(Renter renter) {
		this.renter = renter;
	}

	public Address(String address1, String address2, String zipCode, String city, Enum<Country> country,
			Boolean isPrimary) {
		super();
		this.address1 = address1;
		this.address2 = address2;
		this.zipCode = zipCode;
		this.city = city;
		this.country = country;
		this.isPrimary = isPrimary;
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
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * @param address1 the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @param address2 the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the country
	 */
	public Enum<Country> getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(Enum<Country> country) {
		this.country = country;
	}

	/**
	 * @return the isPrimary
	 */
	public Boolean getIsPrimary() {
		return isPrimary;
	}

	/**
	 * @param isPrimary the isPrimary to set
	 */
	public void setIsPrimary(Boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", address1=" + address1 + ", address2=" + address2 + ", zipCode=" + zipCode
				+ ", city=" + city + ", country=" + country + ", isPrimary=" + isPrimary + ", renter=" + renter
				+ ", mainTenant=" + mainTenant + "]";
	}

}
