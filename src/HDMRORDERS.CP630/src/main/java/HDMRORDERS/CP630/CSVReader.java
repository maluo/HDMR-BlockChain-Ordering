package HDMRORDERS.CP630;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class CSVReader {

	public CSVReader() {
	}

	public int ImportCSVsToDB(int option) {
		int result = 0;

		String jdbcURL = "jdbc:mysql://localhost:3306/db_orders";
		String username = "root";
		String password = "";
		String sql = "";
		String csvFilePath = "";

		csvFilePath = (option == 0) ? Util.orderPath
				: Util.itemPath;
		sql = (option == 0)
				? "INSERT INTO orders (order_num,order_num_post,item_num,unit_price,quantity,balance,shipping,sales_price,subtotal,gross_income,transaction_date) VALUES (?,?,?,?,?,?,?,?,?,?,?)"
				: "INSERT INTO items (product_id,product_name,quantity,comments) VALUES (?,?,?,?)";

		int batchSize = 20;
		Connection connection = null;

		String order_num = "";
		String order_num_2 = "";
		/* Init variables */
		int item_num = 0;
		float unit_price = 0;
		int quantity = 0;
		float balance = 0;
		float shipping = 0;
		float sales_price = 0;
		float subtotal = 0;
		float gross_income = 0;
		String transaction_date = null;

		String comments = "";
		int product_id = 0;

		try {

			connection = DriverManager.getConnection(jdbcURL, username, password);
			connection.setAutoCommit(false);

			PreparedStatement statement = connection.prepareStatement(sql);
			BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
			String lineText = null;

			int count = 0;

			lineReader.readLine(); // skip header line

			while ((lineText = lineReader.readLine()) != null) {
				String[] data = lineText.split(",");
				switch (option) {

				case 0:
					order_num = data[0];
					order_num_2 = data[1];
					try {
						item_num = Integer.parseInt(data[2]);
					} catch (Exception e) {
					}

					unit_price = Float.parseFloat(data[3]);
					try {
						quantity = Integer.parseInt(data[4]);
					} catch (Exception e) {
					}
					try {
						balance = Float.parseFloat(data[5]);
					} catch (Exception e) {
					}

					try {
						shipping = Float.parseFloat(data[6]);
					} catch (Exception e) {
					}

					try {
						sales_price = Float.parseFloat(data[7]);
					} catch (Exception e) {
					}
					subtotal = Float.parseFloat(data[8]);
					gross_income = Float.parseFloat(data[9]);
					try {
						transaction_date = data[10];
					} catch (Exception e) {
					}
					statement.setString(1, order_num);
					statement.setString(2, order_num_2);
					statement.setInt(3, item_num);
					statement.setFloat(4, unit_price);
					statement.setInt(5, quantity);
					statement.setFloat(6, balance);
					statement.setFloat(7, shipping);
					statement.setFloat(8, sales_price);
					statement.setFloat(9, subtotal);
					statement.setFloat(10, gross_income);
					Timestamp sqlTimestamp = null;

					try {
						sqlTimestamp = Timestamp.valueOf(Util.convertStringToTS(transaction_date));
					} catch (Exception e) {
					}
					statement.setTimestamp(11, sqlTimestamp);
					break;
				case 1:
					product_id = Integer.parseInt(data[0]);
					String product_name = data[1];
					quantity = Integer.parseInt(data[2]);
					try {
						comments = data[3];
					} catch (Exception e) {
					}
					statement.setInt(1, product_id);
					statement.setString(2, product_name);
					statement.setInt(3, quantity);
					statement.setString(4, comments);
					break;
				default:
					break;
				}

				statement.addBatch();

				if (count % batchSize == 0) {
					statement.executeBatch();
				}

			}

			lineReader.close();

			// execute the remaining queries
			statement.executeBatch();

			connection.commit();
			connection.close();

		} catch (IOException ex) {
			System.err.println(ex);
		} catch (SQLException ex) {
			ex.printStackTrace();

			try {
				connection.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}