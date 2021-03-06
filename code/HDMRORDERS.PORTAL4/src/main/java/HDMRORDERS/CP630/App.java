package HDMRORDERS.CP630;

import HDMRORDERS.DB.DAO.ItemDao;
import HDMRORDERS.DB.DAO.OrderDao;
import HDMRORDERS.DB.DAO.IMPL.ItemDaoIMPL;
import HDMRORDERS.DB.DAO.IMPL.OrderDaoIMPL;
import HDMRORDERS.DB.DOMAIN.Items;
import HDMRORDERS.DB.DOMAIN.Orders;
import HDMRORDERS.DB.UTIL.GlobalFunc;

public class App {
	public static void main(String[] args) {
		// System.out.println( "Hello World!" );
		
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
		System.out.println(orderDao.OrderProfitService());
		
		
		//dp starts here
		
		// Restful service vs Scala, Apply 0/1 knapsack in JAVA first, could use Scala
		// later

		// Rest service to call some background cmd running like Scala, or db_script
		// upload

		// Get resource ready for the rest backend : )

		/* End of pricing stragegy */

	}
}