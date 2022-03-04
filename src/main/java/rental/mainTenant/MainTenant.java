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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import rental.address.Address;
import rental.contract.Contract;
import rental.enumeration.Gender;
import rental.enumeration.TenantStatus;
import rental.person.Person;

@Entity
@Table(name = "mainTenant")

@AttributeOverride(name = "id", column = @Column(name = "ID"))
public class MainTenant extends Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Enumerated(EnumType.STRING)
	@Column(name = "tenantstatus")
	private TenantStatus mainTenantStatus;

	@OneToMany(mappedBy = "mainTenant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Address> addresses;
	@OneToOne(mappedBy = "mainTenant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Contract contract;

	public MainTenant() {
		super();
	}

	public MainTenant(String fistName, Gender gender) {
		super(fistName, gender);
	}

	public MainTenant(String lastName, String fistName, Gender gender, String email) {
		super(lastName,fistName, gender, email);
	}

	public MainTenant(TenantStatus mainTenantStatus, List<Address> addresses, Contract contract) {
		super();
		this.mainTenantStatus = mainTenantStatus;
		this.addresses = addresses;
		this.contract = contract;
	}

	/**
	 * @return the mainTenantStatus
	 */
	public TenantStatus getMainTenantStatus() {
		return mainTenantStatus;
	}

	/**
	 * Cast the status
	 * 
	 * @param mainTenantStatus the mainTenantStatus to set By default status is
	 *                         ACTIVE
	 */
	public void setMainTenantStatus(String mainTenantStatus) {
		this.mainTenantStatus = TenantStatus.valueOf(mainTenantStatus);
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
		return "MainTenant [mainTenantStatus=" + super.toString() + mainTenantStatus + ", addresses=" + addresses + ", contract="
				+ contract + "]";
	}

}
