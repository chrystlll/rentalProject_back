package rental.utils;

import java.util.Calendar;
import java.util.Date;

import rental.enumeration.DurationType;
import rental.enumeration.ScheduledPaymentType;

public class Utils {

	/**
	 * Return the amount by month
	 * 
	 * @param: DurationType (can be ANNEE, MOIS,SEMAINE)
	 * @param: Amount       (Float)
	 * @return: amountByMonth (Float)
	 */
	public static Float calculateAmountByMonth(DurationType durationType, Float amount) {

		Float amountByMonth = 0f;
		switch (durationType) {
		case ANNEE:
			amountByMonth = amount / 12;
			break;
		case MOIS:
			amountByMonth = amount;
			break;
		case SEMAINE:
			amountByMonth = amount * 4.3f;
			break;
		default:
			break;
		}
		return amountByMonth;
	}

	/**
	 * Return the total amount depending of the period
	 * 
	 * @param: ScheduledPaymentType (can be ANNUEL, SEMESTRIEL, TRIMESTRIEL,MENSUEL)
	 * @param: Amount               (Float) => initial by month
	 * @return: totalAmount (Float)
	 */
	public static Float calculateAmountByPeriod(ScheduledPaymentType schedPayType, Float amountByMonth) {

		Float totalAmount = 0f;
		switch (schedPayType) {
		case ANNUEL:
			totalAmount = amountByMonth * 12;
			break;
		case SEMESTRIEL:
			totalAmount = amountByMonth * 6;
		case TRIMESTRIEL:
			totalAmount = amountByMonth * 3;
		case MENSUEL:
			totalAmount = amountByMonth;
		default:
			break;
		}
		return totalAmount;
	}

	/**
	 * Return the end date depending of the period
	 * 
	 * @param: startDate    (Date)
	 * @param: schedPayType (ScheduledPaymentType) can be ANNUEL,SEMESTRIEL...
	 * @return: endDate (Date)
	 */
	public static Date calculateEndDate(Date startDate, ScheduledPaymentType schedPayType) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);

		switch (schedPayType) {

		case ANNUEL:
			cal.add(Calendar.YEAR, 1);
			break;
		case SEMESTRIEL:
			cal.add(Calendar.MONTH, 6);
			break;
		case TRIMESTRIEL:
			cal.add(Calendar.MONTH, 3);
			break;
		case MENSUEL:
			cal.add(Calendar.MONTH, 1);
			break;
		default:
			break;
		}

		Date endDate = cal.getTime();
		return endDate;

	}

}
