package ec.lab;

import javax.ejb.LocalBean;

import HDMRORDERS.DB.DAO.OrderDao;
import HDMRORDERS.DB.DAO.IMPL.*;
import javax.ejb.Singleton;

/**
 * Session Bean implementation class SBSingleton
 */
@Singleton
@LocalBean
public class SBSingleton implements SBSingletonRemote, SBSingletonLocal {
	private int counter = 0;
	private OrderDao orderImpl = new OrderDaoIMPL();

    /**
     * Default constructor. 
     */
    public SBSingleton() {
    }

	@Override
	public int getCounter() {
		//orderImpl.OrderProfitService();
		return counter;
	}

	@Override
	public int incCounter() {
		counter++;
		return counter;
	}

	@Override
	public String getSBType() {
		return "singleton";
	}
}