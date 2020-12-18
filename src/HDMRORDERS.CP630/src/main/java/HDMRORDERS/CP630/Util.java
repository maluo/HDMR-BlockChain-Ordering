package HDMRORDERS.CP630;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Util {
	
	public static String convertStringToTS(String date) {
		Timestamp ts= new Timestamp(convertStaringToDdate(date).getTime());  
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(ts);
	}
	
	public static Date convertStaringToDdate(String date) {
		Date newDate = null;
		try {
			newDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
		} catch (ParseException e) {}  
		return newDate;
	}
	
	

}
