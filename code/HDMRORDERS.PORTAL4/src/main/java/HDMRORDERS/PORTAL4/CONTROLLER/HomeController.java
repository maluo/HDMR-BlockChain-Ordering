package HDMRORDERS.PORTAL4.CONTROLLER;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import HDMRORDERS.DB.DAO.OrderDao;
import HDMRORDERS.DB.DAO.IMPL.OrderDaoIMPL;
import HDMRORDERS.DB.DOMAIN.Orders;
 
@WebServlet("/")
public class HomeController extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    private OrderDao orderDao = OrderDaoIMPL.getInstance();
     
    public HomeController() {
        // Do Nothing
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        List<Orders> orderList = orderDao.findAllOrders();
 
        request.setAttribute("orderList", orderList);
 
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}