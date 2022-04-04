package rental.mainTenant;

import java.io.Serializable;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import rental.address.Address;
import rental.contract.Contract;
import rental.enumeration.CommonStatus;
import rental.enumeration.Gender;
import rental.person.Person;

@Entity
@Table(name = "mainTenant")

@AttributeOverride(name = "id", column = @Column(name = "ID"))
public class MainTenant extends Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Enumerated(EnumType.STRING)
	@Column(name = "tenantstatus")
	private CommonStatus mainCommonStatus;

	@OneToMany(mappedBy = "mainTenant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Address> addresses;
	
	@OneToMany(mappedBy = "mainTenant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@com.fasterxml.jackson.annotation.JsonManagedReference
	private List<Contract> contracts;

	public MainTenant() {
		super();
	}

	public MainTenant(String fistName, Gender gender) {
		super(fistName, gender);
	}

	public MainTenant(String lastName, String fistName, Gender gender, String email) {
		super(lastName,fistName, gender, email);
	}

	public MainTenant(CommonStatus mainCommonStatus, List<Address> addresses) {
		super();
		this.mainCommonStatus = mainCommonStatus;
		this.addresses = addresses;
	}
	
	
	public void MainTenantCon(CommonStatus mainCommonStatus, List<Contract> contracts) {
		this.mainCommonStatus = mainCommonStatus;
		this.contracts = contracts;
	}

	/**
	 * @return the mainCommonStatus
	 */
	public CommonStatus getMainCommonStatus() {
		return mainCommonStatus;
	}

	/**
	 * Cast the status
	 * 
	 * @param mainCommonStatus the mainCommonStatus to set By default status is
	 *                         ACTIVE
	 */
	public void setMainCommonStatus(String mainCommonStatus) {
		this.mainCommonStatus = CommonStatus.valueOf(mainCommonStatus);
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

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @param mainCommonStatus the mainCommonStatus to set
	 */
	public void setMainCommonStatus(CommonStatus mainCommonStatus) {
		this.mainCommonStatus = mainCommonStatus;
	}

	@Override
	public String toString() {
		return "MainTenant [mainCommonStatus=" + mainCommonStatus + ", addresses=" + addresses + ", contracts="
				+ contracts + "]";
	}

	

}
