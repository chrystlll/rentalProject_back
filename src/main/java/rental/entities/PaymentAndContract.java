package rental.entities;

import rental.contract.Contract;
import rental.payment.Payment;

public class PaymentAndContract {

	private Contract contract;
	private Payment payment;

	public PaymentAndContract() {
		super();
	}

	public PaymentAndContract(Contract contract, Payment payment) {
		super();
		this.contract = contract;
		this.payment = payment;
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
	 * @return the payment
	 */
	public Payment getPayment() {
		return payment;
	}

	/**
	 * @param payment the payment to set
	 */
	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "PaymentAndContract [contract=" + contract + ", payment=" + payment + "]";
	}

}
