
package HDMRORDERS.CP630;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import HDMRORDERS.DB.DAO.ItemDao;
import HDMRORDERS.DB.DAO.OrderDao;
import HDMRORDERS.DB.DAO.IMPL.ItemDaoIMPL;
import HDMRORDERS.DB.DAO.IMPL.OrderDaoIMPL;
import HDMRORDERS.DB.DOMAIN.Items;
import HDMRORDERS.DB.DOMAIN.Orders;
import HDMRORDERS.DB.DOMAIN.Tuple;
import HDMRORDERS.DB.UTIL.GlobalFunc;

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

		List<Orders> orders = orderDao.findAllOrders();// Need to kick out dummies here
		List<Orders> out = orders.stream().filter(x -> (x.getOrderNum().contains("-"))).collect(Collectors.toList());
		List<Orders> in = orders.stream().filter(x -> x.getOrderNum().contains("I")).collect(Collectors.toList());

		Function<Orders, List<Object>> compositeKey = orderRecord -> Arrays.<Object>asList(orderRecord.getOrderNum2(),
				orderRecord.getItems().getProductId());

		Map<List<Object>, Double> mapOut = out.stream().collect(
				Collectors.groupingBy(compositeKey, Collectors.averagingDouble(orderR -> orderR.getUnitPrice())));

		List<Tuple> tuples = new ArrayList<Tuple>();
		for (Entry<List<Object>, Double> entry : mapOut.entrySet()) {
			tuples.add(new Tuple(entry.getKey().toArray()[0].toString(), (int) entry.getKey().toArray()[1],
					(Double) entry.getValue()));
		}

		Map<Integer, Double> sales = tuples.stream().collect(
				Collectors.groupingBy(record -> record.item_num, Collectors.averagingDouble(orderR -> orderR.profit)));

		Map<List<Object>, Double> mapIn = in.stream().collect(
				Collectors.groupingBy(compositeKey, Collectors.averagingDouble(orderR -> orderR.getUnitPrice())));

		tuples = new ArrayList<Tuple>();
		for (Entry<List<Object>, Double> entry : mapIn.entrySet()) {
			tuples.add(new Tuple(entry.getKey().toArray()[0].toString(), (int) entry.getKey().toArray()[1],
					(Double) entry.getValue()));
		}
		Map<Integer, Double> buget_In = tuples.stream().collect(
				Collectors.groupingBy(record -> record.item_num, Collectors.averagingDouble(orderR -> orderR.profit)));

		double profit = 0;
		Object[] keys = buget_In.keySet().toArray();
		for (Object obj : keys) {
			if (!sales.containsKey(obj)) {
				buget_In.remove(obj);
			}
		}

		System.out.println(sales);
		System.out.println(buget_In);

		// dp starts here

		// Restful service vs Scala, Apply 0/1 knapsack in JAVA first, could use Scala
		// later

		// Rest service to call some background cmd running like Scala, or db_script
		// upload

		// Get resource ready for the rest backend : )

		/* End of pricing stragegy */

		int W = 300;
        int val[] = {10, 30, 20};
        int wt[] = {5, 10, 15};
        int n = val.length;
		UboundedKnapsack run = new UboundedKnapsack();
		System.out.println(run.unboundedKnapsack(W, n, val, wt));//Remove boundary

	}
}

class UboundedKnapsack 
{
	
	UboundedKnapsack(){}
     
    private static int max(int i, int j) 
    {
            return (i > j) ? i : j;
    }
     
    // Returns the maximum value with knapsack
    // of W capacity
    static int unboundedKnapsack(int W, int n, 
                                int[] val, int[] wt) 
    {
         
        // dp[i] is going to store maximum value
        // with knapsack capacity i.
        int dp[] = new int[W + 1];
         
        // Fill dp[] using above recursive formula
        for(int i = 0; i <= W; i++){
            for(int j = 0; j < n; j++){
                if(wt[j] <= i){
                    dp[i] = max(dp[i], dp[i - wt[j]] + 
                                val[j]);
                }
            }
        }
        return dp[W];
    }
}
// This code is contributed by Aditya Kumar 