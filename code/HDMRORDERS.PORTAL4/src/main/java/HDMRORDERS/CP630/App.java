package HDMRORDERS.CP630;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
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

		//System.out.println(item.getProductName());// Tested
		// orderDao.saveOrder(o);//Insertion Done

		List<Orders> orders = orderDao.findAllOrders();
		//System.out.println(orders.get(10).getOrderNum());
		//System.out.println(orders.get(10).getOrderNum2());
		
		/*Well done map reduce tricks with Java programming : )*/
		Function<Orders, List<Object>> compositeKey = orderRecord ->
	    Arrays.<Object>asList(orderRecord.getOrderNum2(), orderRecord.getItems().getProductId());
	    
	    //Filter sales orders
	    Map<List<Object>, Double> map =
	    		orders.stream().collect(Collectors.groupingBy(compositeKey, Collectors.averagingDouble(orderR -> orderR.getUnitPrice())));
	    //Filter importing orders
	    
	    //Join orders and reduce as the final result
	    //join the two result set and make an result set class called tuple
	    
	    //convert to a single Tuple list
	    
	    Function<Tuple, List<Object>> singleKey = record -> Arrays.<Object>asList(record.item_num);
	    
	    //Prints out input for Knapsack to a csv file on a shared space - model object
	    
	    //Restful service vs Scala, Apply 0/1 knapsack in JAVA first, could use Scala later
	    
	    /*End of pricing stragegy*/
	    System.out.println(map);;
		
		//List<Orders> out = orders.stream().filter(x -> x.getOrderNum().contains("O")).collect(Collectors.toList());
		//List<Orders> in = orders.stream().filter(x -> x.getOrderNum().contains("I")).collect(Collectors.toList());
		//Map<String, List<Orders>> orderByImport;
		//orderByImport = out.stream().collect(groupingBy(x -> x.get));
		
		/*
		 * Table<String, String, Orders> table = orders.stream().collect(Tables.toTable(
		 * Orders::get, o.getOrderNum2(), o.getUnitPrice() ));
		 */

		//Table<String, String, Float> table = HashBasedTable.create();
		//for (Orders o1 : orders) {
			//table.put(o1.getOrderNum(), o1.getOrderNum2(),
					//(o1.getOrderNum().contains("I")) ? -o1.getUnitPrice() : o1.getUnitPrice());
		//}
		
		//orders.stream().parallel().collect(Collectors.groupingBy(order::getOrderNum(),order::getOrderNum2()));
		// p));
		
		//Map<Tuple, List<Orders>> ordersMapping = orders.stream().parallel()
				  //.collect(groupingBy(Orders :: getOrderNum()));

		//System.out.println(table);

		// orderDao.deleteOrder(98);//The one just inserted - Done. Ready to go for the
		// jsp portal
		// orderDao.deleteOrder(99);//The one just inserted - Done. Ready to go for the
		// jsp portal

	}
}