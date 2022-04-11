package rental.entities;

import rental.contract.Contract;
import rental.scheduledPayment.ScheduledPayment;

public class ScheduledPaymentAndContract {

	private Contract contract;
	private ScheduledPayment scheduledPayment;

	public ScheduledPaymentAndContract() {
		super();
	}

	public ScheduledPaymentAndContract(Contract contract, ScheduledPayment scheduledPayment) {
		super();
		this.contract = contract;
		this.scheduledPayment = scheduledPayment;
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
	public ScheduledPayment getPayment() {
		return scheduledPayment;
	}

	/**
	 * @param payment the payment to set
	 */
	public void setPayment(ScheduledPayment scheduledPayment) {
		this.scheduledPayment = scheduledPayment;
	}

	@Override
	public String toString() {
		return "PaymentAndContract [contract=" + contract + ", scheduledPayment=" + scheduledPayment + "]";
	}

}
