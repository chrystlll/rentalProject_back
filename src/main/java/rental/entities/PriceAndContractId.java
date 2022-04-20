package rental.entities;

import rental.price.Price;

public class PriceAndContractId {
	
	private Price price;
	private Long contractId;
	
	
	public PriceAndContractId() {
		super();
		// TODO Auto-generated constructor stub
	}


	public PriceAndContractId(Price price, Long contractId) {
		super();
		this.price = price;
		this.contractId = contractId;
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


	/**
	 * @return the contractId
	 */
	public Long getContractId() {
		return contractId;
	}


	/**
	 * @param contractId the contractId to set
	 */
	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}


	@Override
	public String toString() {
		return "PriceAndContractId [price=" + price + ", contractId=" + contractId + "]";
	}
	
	
	

}
