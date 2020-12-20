package HDMRORDERS.CP630;

import java.util.List;
import java.util.stream.Collectors;

import HDMRORDERS.PORTAL4.DAO.ItemDao;
import HDMRORDERS.PORTAL4.DAO.OrderDao;
import HDMRORDERS.PORTAL4.DAO.IMPL.ItemDaoIMPL;
import HDMRORDERS.PORTAL4.DAO.IMPL.OrderDaoIMPL;
import HDMRORDERS.PORTAL4.DOMAIN.Items;
import HDMRORDERS.PORTAL4.DOMAIN.Orders;
import HDMRORDERS.PORTAL4.UTIL.GlobalFunc;

public class App 
{
    public static void main( String[] args )
    {
        //System.out.println( "Hello World!" );
    	@SuppressWarnings("unused")
		OrderDao orderDao = OrderDaoIMPL.getInstance();
    	ItemDao itemDao = ItemDaoIMPL.getInstance();
        
    	String orderNum = "O0008-0003";
    	String orderNum2 = "I0003";
    	Float unitPrice = Float.valueOf(0);
    	Integer quantity = 0;
    	Float balance = Float.valueOf(0);
    	Float shipping = Float.valueOf(0);
    	Float salesPrice = Float.valueOf(0);
    	Float subtotal = Float.valueOf(0);
    	Float grossIncome = Float.valueOf(0);
    	Items item = itemDao.findItemById(1);
    	
    	Orders o = new Orders(orderNum,orderNum2,unitPrice,quantity,balance,shipping,salesPrice,subtotal,grossIncome);
    	o.setTransactionDate(GlobalFunc.convertStrToDdate("20/05/2020"));
    	o.setItems(item);
    	
    	System.out.println(item.getProductName());//Tested
    	
    	//orderDao.saveOrder(o);//Insertion Done
    	
    	List<Orders> orders = orderDao.findAllOrders();
    	System.out.println(orders.get(10).getOrderNum());
    	System.out.println(orders.get(10).getOrderNum2());
    	orders = orders.stream().filter(x -> x.getOrderNum().contains("O")).collect(Collectors.toList());
    	System.out.println(orders.size());
    	
    	//orderDao.deleteOrder(98);//The one just inserted - Done.  Ready to go for the jsp portal
    	//orderDao.deleteOrder(99);//The one just inserted - Done.  Ready to go for the jsp portal
    	
    }
}