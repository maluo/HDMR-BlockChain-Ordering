package ec.REPOSITORY;

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
import ec.CALCULATION.ItemUnit;

public class OrderRepo {
	
	private OrderDao orderDaoImpl = null;
	private ItemDao itemDaoImpl = null;
	
	public OrderRepo() {
		orderDaoImpl = OrderDaoIMPL.getInstance();
		itemDaoImpl = ItemDaoIMPL.getInstance();
	}
	
	public List<ItemUnit> getKnapsackInput(){
		Map<Integer,Double> budget_In, sales;
		budget_In = getImportAvgPerItem();
		sales = getSalesAvgPerItem();
		
		Object[] keys = budget_In.keySet().toArray();
		for (Object obj : keys) {
			if (!sales.containsKey(obj)) {
				budget_In.remove(obj);
			}
		}
		
		List<Items> items= itemDaoImpl.findAllItems();
		HashMap<Integer,String> itemMaps= new HashMap<Integer,String>();
		for(Items i : items) {
			itemMaps.put(i.getId(), i.getProductName());
		}
		
		List<ItemUnit> units= new ArrayList<ItemUnit>();
		ItemUnit unit = null;
		for (Entry<Integer, String> entry : itemMaps.entrySet()) {
			try {
			unit = new ItemUnit(entry.getValue(),budget_In.get(entry.getKey()),sales.get(entry.getKey()));
			units.add(unit);
			}catch(Exception e) {}
		}
		
		return units;
	}
	
	public Map<Integer,Double> getSalesAvgPerItem(){
		
		List<Tuple> tuples = new ArrayList<Tuple>();
		List<Orders> out = orderDaoImpl.findAllOrders().stream().filter(x -> (x.getOrderNum().contains("-")))
				.collect(Collectors.toList());

		Function<Orders, List<Object>> compositeKey = orderRecord -> Arrays.<Object>asList(orderRecord.getOrderNum2(),
				orderRecord.getItems().getProductId());

		Map<List<Object>, Double> mapOut = out.stream().collect(
				Collectors.groupingBy(compositeKey, Collectors.averagingDouble(orderR -> orderR.getUnitPrice())));
		
		for (Entry<List<Object>, Double> entry : mapOut.entrySet()) {
			tuples.add(new Tuple(entry.getKey().toArray()[0].toString(), (int) entry.getKey().toArray()[1],
					(Double) entry.getValue()));
		}

		Map<Integer, Double> sales = tuples.stream().collect(
				Collectors.groupingBy(record -> record.item_num, Collectors.averagingDouble(orderR -> orderR.profit)));
		
		return sales;
	} 
	
	public Map<Integer,Double> getImportAvgPerItem(){
		
		List<Tuple> tuples = new ArrayList<Tuple>();
		List<Orders> in = orderDaoImpl.findAllOrders().stream().filter(x -> x.getOrderNum().contains("I")).collect(Collectors.toList());
		
		Function<Orders, List<Object>> compositeKey = orderRecord -> Arrays.<Object>asList(orderRecord.getOrderNum2(),
				orderRecord.getItems().getProductId());
		
		Map<List<Object>, Double> mapIn = in.stream().collect(
				Collectors.groupingBy(compositeKey, Collectors.averagingDouble(orderR -> orderR.getUnitPrice())));

		tuples = new ArrayList<Tuple>();
		for (Entry<List<Object>, Double> entry : mapIn.entrySet()) {
			tuples.add(new Tuple(entry.getKey().toArray()[0].toString(), (int) entry.getKey().toArray()[1],
					(Double) entry.getValue()));
		}
		Map<Integer, Double> buget_In = tuples.stream().collect(
				Collectors.groupingBy(record -> record.item_num, Collectors.averagingDouble(orderR -> orderR.profit)));
		
		return buget_In;
	}

}
