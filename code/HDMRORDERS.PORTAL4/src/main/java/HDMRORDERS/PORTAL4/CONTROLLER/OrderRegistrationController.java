package HDMRORDERS.PORTAL4.CONTROLLER;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import HDMRORDERS.DB.DAO.ItemDao;
import HDMRORDERS.DB.DAO.OrderDao;
import HDMRORDERS.DB.DAO.IMPL.ItemDaoIMPL;
import HDMRORDERS.DB.DAO.IMPL.OrderDaoIMPL;
import HDMRORDERS.DB.DOMAIN.Orders;
import HDMRORDERS.DB.UTIL.GlobalFunc;

@WebServlet("/order/register")
public class OrderRegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private OrderDao orderDao = OrderDaoIMPL.getInstance();
	private ItemDao itemDao = ItemDaoIMPL.getInstance();

	public OrderRegistrationController() {
		// Do Nothing
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String orderId = request.getParameter("id");
		String order_num = request.getParameter("order_num");
		String order_num_post = request.getParameter("order_num_post");
		Integer item_num = Integer.parseInt(request.getParameter("item_num"));
		Float unit_price = Float.parseFloat(request.getParameter("unit_price"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		Float shipping = Float.parseFloat(request.getParameter("shipping"));

		Float balance = (order_num == order_num_post) ? 1 : -1 * unit_price * quantity;
		Float sales_price = balance + shipping;
		Float subtotal = sales_price;
		Float gross_income = (float) 0.0;
		Date transaction_date = GlobalFunc.convertStrToDdate(request.getParameter("transaction_date"));
		Date sys_date = GlobalFunc.convertStrToDdate(request.getParameter("sys_date"));

		Orders order = new Orders(order_num, order_num_post, unit_price, quantity, balance, shipping, sales_price,
				subtotal, gross_income, transaction_date);
		order.setItems(itemDao.findItemById(item_num));
		order.setSysDate(sys_date);

		if (orderId == null || orderId == "")
			orderDao.saveOrder(order);
		else {
			Integer id = Integer.parseInt(orderId);
			Orders original = orderDao.findOrderById(id);
			order.setSysDate(original.getSysDate());
			order.setTransactionDate(original.getTransactionDate());
			order.setId(id);
			orderDao.updateOrder(order);
		}

		response.sendRedirect(request.getContextPath() + "/");
	}

}