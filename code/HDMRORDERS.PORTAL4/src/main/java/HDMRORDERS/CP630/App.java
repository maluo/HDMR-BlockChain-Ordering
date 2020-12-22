package HDMRORDERS.CP630;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import HDMRORDERS.PORTAL4.DAO.ItemDao;
import HDMRORDERS.PORTAL4.DAO.OrderDao;
import HDMRORDERS.PORTAL4.DAO.IMPL.ItemDaoIMPL;
import HDMRORDERS.PORTAL4.DAO.IMPL.OrderDaoIMPL;
import HDMRORDERS.PORTAL4.DOMAIN.Items;
import HDMRORDERS.PORTAL4.DOMAIN.Orders;
import HDMRORDERS.PORTAL4.DOMAIN.Tuple;
import HDMRORDERS.PORTAL4.UTIL.GlobalFunc;

public class App {
	public static void main(String[] args) {
		// System.out.println( "Hello World!" );
		@SuppressWarnings("unused")
		OrderDao orderDao = OrderDaoIMPL.getInstance();
		ItemDao itemDao = ItemDaoIMPL.getInstance();

		String orderNum = "O0008-0003";
		String orderNum2 = "I0003";
		Float unitPrice = Float.valueOf(0);
		Integer quantity = 0;
		Float balance = Float.valueOf(0);
		Float shipping = Float.valueOf(0);
		Float salesPrice = Float.valueOf(0);
		Float subtotal = Float.valueOf(0);
		Float grossIncome = Float.valueOf(0);
		Items item = itemDao.findItemById(1);

		Orders o = new Orders(orderNum, orderNum2, unitPrice, quantity, balance, shipping, salesPrice, subtotal,
				grossIncome);
		o.setTransactionDate(GlobalFunc.convertStrToDdate("20/05/2020"));
		o.setItems(item);

		//Orders o1 = orderDao.findOrderById(92);
		//System.out.println(o1.getItems().getProductId());
		orderDao.OrderProfitService();
		
		
		//dp starts here
		
		// Restful service vs Scala, Apply 0/1 knapsack in JAVA first, could use Scala
		// later

		// Rest service to call some background cmd running like Scala, or db_script
		// upload

		// Get resource ready for the rest backend : )

		/* End of pricing stragegy */

	}
}