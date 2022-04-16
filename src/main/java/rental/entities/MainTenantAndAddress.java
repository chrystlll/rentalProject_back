package rental.entities;

import rental.address.Address;
import rental.mainTenant.MainTenant;

public class MainTenantAndAddress {

	private Address address;
	private MainTenant mainTenant;

	public MainTenantAndAddress(Address address, MainTenant mainTenant) {
		super();
		this.address = address;
		this.mainTenant = mainTenant;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * @return the mainTenant
	 */
	public MainTenant getMainTenant() {
		return mainTenant;
	}

	/**
	 * @param mainTenant the mainTenant to set
	 */
	public void setMainTenant(MainTenant mainTenant) {
		this.mainTenant = mainTenant;
	}

}
