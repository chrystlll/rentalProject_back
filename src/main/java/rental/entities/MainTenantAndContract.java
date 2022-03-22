package rental.entities;


import rental.contract.Contract;
import rental.mainTenant.MainTenant;



public class MainTenantAndContract {
	private Contract contract;
    private MainTenant mainTenant;
    
    
	public MainTenantAndContract(Contract contract, MainTenant mainTenant) {
		super();
		this.contract = contract;
		this.mainTenant = mainTenant;
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
