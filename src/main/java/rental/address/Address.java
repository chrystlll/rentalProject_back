package rental.address;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import rental.enumeration.Country;
import rental.mainTenant.MainTenant;
import rental.place.Place;
import rental.renter.Renter;

@Entity
@Table(name = "address")
public class Address {
	@Id
	@SequenceGenerator(name = "address_sequence", sequenceName = "address_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_sequence")
	private Long id;
	private String address1;
	private String address2;
	private String zipCode;
	private String city;
	@Enumerated(EnumType.STRING)
	private Country country;
	private Boolean isPrimary;

	@OneToOne(fetch = FetchType.LAZY, optional = true)
	private Renter renter;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private MainTenant mainTenant;

	@OneToMany(mappedBy = "address", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Place> places;

	public Address() {
		super();
	}

	public Address(Long id, String address1, String address2, String zipCode, String city, Country country,
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

	public Address(String address1, Country country, Boolean isPrimary) {
		super();
		this.address1 = address1;
		this.country = country;
		this.isPrimary = isPrimary;
	}

	public Address(String address1, Country country, Boolean isPrimary, MainTenant mainTenant, Renter renter) {
		super();
		this.address1 = address1;
		this.country = country;
		this.isPrimary = isPrimary;
		this.mainTenant = mainTenant;
		this.renter = renter;
	}

	public Address(String address1, String address2, String zipCode, String city, Country country, Boolean isPrimary) {
		super();
		this.address1 = address1;
		this.address2 = address2;
		this.zipCode = zipCode;
		this.city = city;
		this.country = country;
		this.isPrimary = isPrimary;
	}

	public Address(String address1, String address2, String zipCode, String city, Country country, Boolean isPrimary,
			Renter renter) {
		super();
		this.address1 = address1;
		this.address2 = address2;
		this.zipCode = zipCode;
		this.city = city;
		this.country = country;
		this.isPrimary = isPrimary;
		this.renter = renter;
	}

	public Address(String address1, String address2, String zipCode, String city, Country country, Boolean isPrimary,
			MainTenant mainTenant) {
		super();
		this.address1 = address1;
		this.address2 = address2;
		this.zipCode = zipCode;
		this.city = city;
		this.country = country;
		this.isPrimary = isPrimary;
		this.mainTenant = mainTenant;
	}

	public Address(Long id, String address1, String address2, String zipCode, String city, Country country,
			Boolean isPrimary, Renter renter, MainTenant mainTenant, List<Place> places) {
		super();
		this.id = id;
		this.address1 = address1;
		this.address2 = address2;
		this.zipCode = zipCode;
		this.city = city;
		this.country = country;
		this.isPrimary = isPrimary;
		this.renter = renter;
		this.mainTenant = mainTenant;
		this.places = places;
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
	public void setCountry(Country country) {
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

	/**
	 * @return the places
	 */
	public List<Place> getPlaces() {
		return places;
	}

	/**
	 * @param places the places to set
	 */
	public void setPlaces(List<Place> places) {
		this.places = places;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", address1=" + address1 + ", address2=" + address2 + ", zipCode=" + zipCode
				+ ", city=" + city + ", country=" + country + ", isPrimary=" + isPrimary + ", renter=" + renter
				+ ", mainTenant=" + mainTenant + ", places=" + places + "]";
	}

}
