package rental.rentCertificate;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import rental.enumeration.SendingType;

@Entity
@Table(name = "rentCertificate")
public class RentCertificate {
	@Id
	@SequenceGenerator(name = "rentCertificate_sequence", sequenceName = "rentCertificate_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rentCertificate_sequence")
	private Long id;
	private Date startDate;
	private Date endDate;
	@Enumerated(EnumType.STRING)
	private SendingType sendingType;
	private Date sendingDate;
	private Boolean isSend;

	public RentCertificate() {
		super();
	}

	public RentCertificate(Long id, Date startDate, Date endDate, SendingType sendingType, Date sendingDate, Boolean isSend) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.sendingType = sendingType;
		this.sendingDate = sendingDate;
		this.isSend = isSend;
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
	 * @return the sendingType
	 */
	public SendingType getSendingType() {
		return sendingType;
	}

	/**
	 * @param sendingType the sendingType to set
	 */
	public void setSendingType(SendingType sendingType) {
		this.sendingType = sendingType;
	}

	/**
	 * @return the sendingDate
	 */
	public Date getSendingDate() {
		return sendingDate;
	}

	/**
	 * @param sendingDate the sendingDate to set
	 */
	public void setSendingDate(Date sendingDate) {
		this.sendingDate = sendingDate;
	}

	/**
	 * @return the isSend
	 */
	public Boolean getIsSend() {
		return isSend;
	}

	/**
	 * @param isSend the isSend to set
	 */
	public void setIsSend(Boolean isSend) {
		this.isSend = isSend;
	}

	@Override
	public String toString() {
		return "Lease [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", sendingType=" + sendingType
				+ ", sendingDate=" + sendingDate + ", isSend=" + isSend + "]";
	}

}
