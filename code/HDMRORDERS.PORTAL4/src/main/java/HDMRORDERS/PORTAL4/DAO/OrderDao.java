package HDMRORDERS.PORTAL4.DAO;

import java.util.List;
import java.util.Map;

import HDMRORDERS.PORTAL4.DOMAIN.Orders;

public interface OrderDao {
	
	Integer saveOrder(Orders order);

	void updateOrder(Orders order);

	void deleteOrder(Integer id);

	Orders findOrderById(Integer id);

	List<Orders> findAllOrders();
	
	Map<Integer, Double> OrderProfitService();

}