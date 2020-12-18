package HDMRORDERS.CP630.DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemDao implements IItemDao {

	public PreparedStatement setOrderImports(PreparedStatement statement, String[] data) throws SQLException {
		// TODO Auto-generated method stub
		String product_name = data[1];
		int quantity = Integer.parseInt(data[2]);
		String comments = "";
		try {
			comments = data[3];
		} catch (Exception e) {
		}
		statement.setString(1, product_name);
		statement.setInt(2, quantity);
		statement.setString(3, comments);

		return null;
	}

}
