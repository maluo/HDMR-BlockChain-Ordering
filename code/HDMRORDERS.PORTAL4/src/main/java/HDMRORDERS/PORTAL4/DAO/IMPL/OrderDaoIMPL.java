package HDMRORDERS.PORTAL4.DAO.IMPL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import HDMRORDERS.PORTAL4.DAO.OrderDao;
import HDMRORDERS.PORTAL4.DOMAIN.Orders;
import HDMRORDERS.PORTAL4.DOMAIN.Tuple;
import HDMRORDERS.PORTAL4.UTIL.HibernateUtil;

public class OrderDaoIMPL implements OrderDao {

	private static OrderDaoIMPL orderDaoImpl = null;
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public Integer saveOrder(Orders order) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Integer id = (Integer) session.save(order);
		transaction.commit();
		session.close();
		return id;
	}

	@Override
	public void updateOrder(Orders order) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(order);
		transaction.commit();
		session.close();

	}

	@Override
	public void deleteOrder(Integer id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Orders customer = session.get(Orders.class, id);
		session.delete(customer);
		transaction.commit();
		session.close();
	}

	@Override
	public Orders findOrderById(Integer id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		Orders order = session.get(Orders.class, id);
		session.close();

		return order;
	}

	@Override
	public List<Orders> findAllOrders() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		List<Orders> orderList = session.createCriteria(Orders.class).list();
		session.close();
		return orderList;
	}

	public Map<Integer,Double> OrderProfitService() {
		List<Orders> orders = orderDaoImpl.findAllOrders();// Need to kick out dummies here
		List<Orders> out = orders.stream().filter(x -> (x.getOrderNum().contains("-")))
				.collect(Collectors.toList());
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
					-(Double) entry.getValue()));
		}
		Map<Integer, Double> buget_In = tuples.stream().collect(
				Collectors.groupingBy(record -> record.item_num, Collectors.averagingDouble(orderR -> orderR.profit)));

		Map<Integer, Double> combined = new HashMap<Integer, Double>();
		double profit = 0;
		for (Entry<Integer, Double> entry : sales.entrySet()) {
			profit = entry.getValue() + buget_In.get(entry.getKey());
			combined.put(entry.getKey(), profit);
		}

		return combined;
		// Just start dp here
	}

	public static OrderDao getInstance() {
		if (orderDaoImpl == null)
			orderDaoImpl = new OrderDaoIMPL();

		return orderDaoImpl;
	}

}