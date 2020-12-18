package HDMRORDERS.HDMRORDERS.CP630.DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import HDMRORDERS.HDMRORDERS.CP630.Util;

public class OrderDao implements IOrderDao {

	private Util util = new Util();

	public PreparedStatement setOrderImports(PreparedStatement statement, String[] data) throws SQLException {
		// TODO Auto-generated method stub
		String order_num = data[0];
		int item_num = Integer.parseInt(data[1]);
		float unit_price = Float.parseFloat(data[2]);
		int quantity = Integer.parseInt(data[3]);
		float balance = Float.parseFloat(data[4]);
		float shipping = Float.parseFloat(data[5]);
		float sales_price = Float.parseFloat(data[6]);
		float subtotal = Float.parseFloat(data[7]);
		float gross_income = Float.parseFloat(data[8]);
		String transaction_date = data[9];

		statement.setString(1, order_num);
		statement.setInt(2, item_num);
		statement.setFloat(3, unit_price);
		statement.setInt(4, quantity);
		statement.setFloat(5, balance);
		statement.setFloat(6, shipping);
		statement.setFloat(7, sales_price);
		statement.setFloat(8, subtotal);
		statement.setFloat(9, gross_income);
		Timestamp sqlTimestamp = null;
		try {
			sqlTimestamp = Timestamp.valueOf(util.convertStringToTS(transaction_date));
		} catch (Exception e) {
		}
		statement.setTimestamp(10, sqlTimestamp);
		return statement;
	}

}
