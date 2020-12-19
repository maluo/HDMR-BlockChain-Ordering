package HDMRORDERS.CP630.PORTAL3.CONTROLLER;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import HDMRORDERS.CP630.PORTAL3.DAO.OrderDao;
import HDMRORDERS.CP630.PORTAL3.DAO.IMPL.OrderDaoIMPL;
import HDMRORDERS.CP630.PORTAL3.DOMAIN.Orders;
 
 
@WebServlet("/customer/register")
public class OrderRegistrationController extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    private OrderDao orderDao = OrderDaoIMPL.getInstance();
 
    public OrderRegistrationController() {
        // Do Nothing
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/").forward(request, response);
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        String custId = request.getParameter("id");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
 
        Orders order = null; 
        		//new Orders(firstName, lastName, email, mobile);
 
        if (custId == null || custId == "")
        	orderDao.saveOrder(order);
        else {
            Integer id = Integer.parseInt(custId);
            order.setId(id);
            orderDao.updateOrder(order);
        }
 
        response.sendRedirect(request.getContextPath() + "/");
    }
 
}
