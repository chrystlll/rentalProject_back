package rental.mainTenant;

import java.util.List;

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
import rental.person.Person;

@Entity
@Table(name = "mainTenant")
public class MainTenant extends Person {

	@Enumerated(EnumType.STRING)
	@Column(name = "commonStatus")
	private CommonStatus commonStatus;

	@OneToMany(mappedBy = "mainTenant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Address> addresses;

	@OneToMany(mappedBy = "mainTenant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Contract> contracts;

	public MainTenant() {
		super();
	}
	
	

	public MainTenant(String lastName, String firstName, String email,CommonStatus commonStatus) {
		super(lastName,firstName,email);
		this.commonStatus = commonStatus;
	}



	public MainTenant(CommonStatus commonStatus, List<Address> addresses, List<Contract> contracts) {
		super();
		this.commonStatus = commonStatus;
		this.addresses = addresses;
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

	@Override
	public String toString() {
		return "MainTenant [commonStatus=" + commonStatus + ", addresses=" + addresses + ", contracts=" + contracts
				+ "]";
	}

}
