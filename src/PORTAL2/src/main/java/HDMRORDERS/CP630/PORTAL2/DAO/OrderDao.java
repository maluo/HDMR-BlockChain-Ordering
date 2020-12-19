package HDMRORDERS.CP630.PORTAL2.DAO;

import java.util.List;

import HDMRORDERS.CP630.PORTAL2.DOMAIN.Orders;

public interface OrderDao {
	
	long saveOrder(Orders order);

	void updateOrder(Orders order);

	void deleteOrder(Integer id);

	Orders findOrderById(Integer id);

	List<Orders> findAllOrders();

}
