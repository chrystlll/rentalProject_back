package rental.payment;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import rental.enumeration.PaymentType;

@Entity
@Table(name = "payment")
public class Payment {
	@Id
	@SequenceGenerator(name = "payment_sequence", sequenceName = "payment_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_sequence")
	private Long id;
	private Date startDate;
	private Date endDate;
	@Enumerated(EnumType.STRING)
	private PaymentType paymentType;
	private Date paymentDate;

	public Payment() {
		super();
	}

	

	public Payment(Long id, Date startDate, Date endDate, PaymentType paymentType, Date paymentDate) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.paymentType = paymentType;
		this.paymentDate = paymentDate;
	}



	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	


	/**
	 * @return the paymentType
	 */
	public PaymentType getPaymentType() {
		return paymentType;
	}



	/**
	 * @param paymentType the paymentType to set
	 */
	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}



	/**
	 * @return the paymentDate
	 */
	public Date getPaymentDate() {
		return paymentDate;
	}



	/**
	 * @param paymentDate the paymentDate to set
	 */
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}



	@Override
	public String toString() {
		return "Payment [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", paymentType="
				+ paymentType + ", paymentDate=" + paymentDate + "]";
	}

	

}
