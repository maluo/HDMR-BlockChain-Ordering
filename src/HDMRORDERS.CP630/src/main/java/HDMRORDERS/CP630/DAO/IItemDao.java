package HDMRORDERS.CP630.DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface IItemDao {

	public PreparedStatement setOrderImports(PreparedStatement statement, String[] data) throws SQLException;

}
