package ca.ulaval.glo4002.billing.domain;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateManager {

	static String format = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

	public Date stringToDate(String string) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		try {
			return dateFormat.parse(string);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
		// courtesy to
		// stackoverflow.com/questions/35952809/java-convert-string-in-iso-instant-format-to-date
	}

	public static String dateToString(Date date) {
		return new SimpleDateFormat(format).format(date);
	}

	public static String defaultDate() {
		return dateToString(new Date());
	}

}
