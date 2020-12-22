package HDMRORDERS.PORTAL4.CONTROLLER;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import HDMRORDERS.PORTAL4.DAO.CustomerDao;
import HDMRORDERS.PORTAL4.DAO.OrderDao;
import HDMRORDERS.PORTAL4.DAO.IMPL.CustomerDaoImpl;
import HDMRORDERS.PORTAL4.DAO.IMPL.OrderDaoIMPL;

@WebServlet("/order/delete")
public class DeleteOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderDao orderDao = OrderDaoIMPL.getInstance();

	public DeleteOrderController() {
		// Do Nothing
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String custId = request.getParameter("orderId");

		if (custId == "" || custId == null)
			request.getRequestDispatcher("/").forward(request, response);
		else {
			Integer id = Integer.parseInt(custId);
			orderDao.deleteOrder(id);
			response.sendRedirect(request.getContextPath() + "/");
		}
	}
}
