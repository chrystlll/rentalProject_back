package rental.entities;

import rental.contract.Contract;
import rental.price.Price;

public class PriceAndContract {

	private Contract contract;
	private Price price;

	public PriceAndContract() {
		super();
	}

	public PriceAndContract(Contract contract, Price price) {
		super();
		this.contract = contract;
		this.price = price;
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
	 * @return the price
	 */
	public Price getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Price price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "PriceAndContract [contract=" + contract + ", price=" + price + "]";
	}

}
