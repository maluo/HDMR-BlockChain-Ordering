package HDMRORDERS.CP630.PORTAL3.DAO;

import java.util.List;

import HDMRORDERS.CP630.PORTAL3.DOMAIN.Orders;

public interface OrderDao {
	
	Integer saveOrder(Orders order);

	void updateOrder(Orders order);

	void deleteOrder(Integer id);

	Orders findOrderById(Integer id);

	List<Orders> findAllOrders();

}