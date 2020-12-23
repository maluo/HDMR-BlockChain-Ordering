package HDMRORDERS.PORTAL4.CONTROLLER;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import HDMRORDERS.CALCULATION.ContinousKnapsackWrapper;
import HDMRORDERS.DB.DAO.OrderDao;
import HDMRORDERS.DB.DAO.IMPL.OrderDaoIMPL;
import HDMRORDERS.REPOSITORY.OrderRepo;

@WebServlet("/order/dash")
public class DashboardController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OrderDao orderDao = OrderDaoIMPL.getInstance();
    OrderRepo repo = new OrderRepo();;

    public DashboardController() {
        // Do Nothing
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    		Integer budget = Integer.parseInt(request.getParameter("budget"));
    		
    		ContinousKnapsackWrapper o = new ContinousKnapsackWrapper(budget,repo.getKnapsackInput());
            
            request.setAttribute("budgetPlan", o.getBuf());

            request.getRequestDispatcher("/").forward(request, response);
    }
}
