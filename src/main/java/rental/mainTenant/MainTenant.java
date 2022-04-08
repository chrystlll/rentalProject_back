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
	@Column(name = "commonStatus")
	private CommonStatus commonStatus;

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

	public MainTenant(CommonStatus commonStatus, List<Address> addresses) {
		super();
		this.commonStatus = commonStatus;
		this.addresses = addresses;
	}
	
	
	public void MainTenantCon(CommonStatus commonStatus, List<Contract> contracts) {
		this.commonStatus = commonStatus;
		this.contracts = contracts;
	}

	

	/**
	 * @return the commonStatus
	 */
	public CommonStatus getCommonStatus() {
		return commonStatus;
	}

	/**
	 * @param commonStatus the commonStatus to set
	 */
	public void setCommonStatus(CommonStatus commonStatus) {
		this.commonStatus = commonStatus;
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
	 * @param commonStatus the commonStatus to set
	 */
	public void setMainCommonStatus(CommonStatus commonStatus) {
		this.commonStatus = commonStatus;
	}

	@Override
	public String toString() {
		return "MainTenant [commonStatus=" + commonStatus + ", addresses=" + addresses + ", contracts="
				+ contracts + "]";
	}

	

}
