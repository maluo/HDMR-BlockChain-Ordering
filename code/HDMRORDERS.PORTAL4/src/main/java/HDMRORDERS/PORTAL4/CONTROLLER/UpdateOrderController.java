package HDMRORDERS.PORTAL4.CONTROLLER;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import HDMRORDERS.PORTAL4.DAO.OrderDao;
import HDMRORDERS.PORTAL4.DAO.IMPL.OrderDaoIMPL;
import HDMRORDERS.PORTAL4.DOMAIN.Orders;

@WebServlet("/order/update")
public class UpdateOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderDao orderDao = OrderDaoIMPL.getInstance();
	
	public UpdateOrderController() {
		// Do Nothing
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String custId = request.getParameter("orderId");

		if (custId == "" || custId == null)
			request.getRequestDispatcher("/").forward(request, response);
		else {
			Integer id = Integer.parseInt(custId);
			Orders customer = orderDao.findOrderById(id);
			request.setAttribute("order", customer);

			request.getRequestDispatcher("/").forward(request, response);
		}
	}
}
