package HDMRORDERS.PORTAL4.CONTROLLER;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import HDMRORDERS.PORTAL4.DAO.OrderDao;
import HDMRORDERS.PORTAL4.DAO.IMPL.OrderDaoIMPL;
import HDMRORDERS.PORTAL4.DOMAIN.Orders;
import HDMRORDERS.PORTAL4.UTIL.GlobalFunc;
 
 
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
 
	        String orderId = request.getParameter("id");
	        String order_num = request.getParameter("order_num");
	        String order_num_post = request.getParameter("order_num_post");
	        Integer item_num = Integer.parseInt(request.getParameter("item_num"));
	        Float unit_price = Float.parseFloat(request.getParameter("unit_price"));
	        int quantity = Integer.parseInt(request.getParameter("quantity"));
	        Float balance = Float.parseFloat(request.getParameter("balance"));
	        Float shipping = Float.parseFloat(request.getParameter("shipping"));
	        Float sales_price = Float.parseFloat(request.getParameter("sales_price"));
	        Float subtotal = Float.parseFloat(request.getParameter("subtotal"));
	        Float gross_income = Float.parseFloat(request.getParameter("gross_income"));
	        Date transaction_date = GlobalFunc.convertStrToDdate(request.getParameter("transaction_date"));
 
	        Orders order = new Orders(order_num,order_num_post,item_num,unit_price,quantity,balance,shipping,sales_price,subtotal,gross_income,transaction_date);
 
        if (orderId == null || orderId == "")
        	orderDao.saveOrder(order);
        else {
            Integer id = Integer.parseInt(orderId);
            order.setId(id);
            orderDao.updateOrder(order);
        }
 
        response.sendRedirect(request.getContextPath() + "/");
    }
 
}