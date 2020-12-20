package HDMRORDERS.PORTAL4.DAO.IMPL;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import HDMRORDERS.PORTAL4.DAO.OrderDao;
import HDMRORDERS.PORTAL4.DOMAIN.Orders;
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
	
    public static OrderDao getInstance() {
        if(orderDaoImpl == null)
        	orderDaoImpl = new OrderDaoIMPL();
         
        return orderDaoImpl;
    }

}