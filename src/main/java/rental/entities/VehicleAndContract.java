package rental.entities;

import rental.contract.Contract;
import rental.vehicle.Vehicle;

public class VehicleAndContract {

	private Contract contract;
	private Vehicle vehicle;

	public VehicleAndContract() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VehicleAndContract(Contract contract, Vehicle vehicle) {
		super();
		this.contract = contract;
		this.vehicle = vehicle;
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
	 * @return the vehicle
	 */
	public Vehicle getVehicle() {
		return vehicle;
	}

	/**
	 * @param vehicle the vehicle to set
	 */
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	@Override
	public String toString() {
		return "VehicleAndContract [contract=" + contract + ", vehicle=" + vehicle + "]";
	}

}
