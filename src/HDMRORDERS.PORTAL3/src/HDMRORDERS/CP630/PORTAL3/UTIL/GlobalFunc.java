package HDMRORDERS.CP630.PORTAL3.UTIL;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class GlobalFunc {
	public static String convertStringToTS(String date) {
		Timestamp ts = new Timestamp(convertStrToDdate(date).getTime());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(ts);
	}

	public static Date convertStrToDdate(String date) {
		Date newDate = null;
		try {
			newDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
		} catch (ParseException e) {
		}
		return newDate;
	}
}
