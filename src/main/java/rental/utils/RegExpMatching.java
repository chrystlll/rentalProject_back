package rental.utils;

public class RegExpMatching {
	
	public static boolean isValidDate( String date ) {				
		// jj/mm/aaaa    jj/mm/aa
		String regExp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/(19|20)?([0-9]{2})";
		return date.matches( regExp );
	}
	
	public static boolean isValidEmail( String email ) {
		String regExp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
		return email.matches( regExp );
	}

}
