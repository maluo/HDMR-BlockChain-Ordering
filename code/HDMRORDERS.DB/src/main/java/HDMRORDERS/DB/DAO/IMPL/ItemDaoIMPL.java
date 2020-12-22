package HDMRORDERS.DB.DAO.IMPL;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import HDMRORDERS.DB.DAO.ItemDao;
import HDMRORDERS.DB.DOMAIN.Items;
import HDMRORDERS.DB.UTIL.HibernateUtil;

public class ItemDaoIMPL implements ItemDao{

	private static ItemDaoIMPL itemDaoImpl = null;
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	
    public static ItemDao getInstance() {
        if(itemDaoImpl == null)
        	itemDaoImpl = new ItemDaoIMPL();
         
        return itemDaoImpl;
    }


	
	public long saveItem(Items order) {
		// TODO Auto-generated method stub
		return 0;
	}


	
	public void updateItem(Items order) {
		// TODO Auto-generated method stub
		
	}


	
	public void deleteItem(Integer id) {
		// TODO Auto-generated method stub
		
	}


	
	public Items findItemById(Integer id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		Items item = session.get(Items.class, id);
		session.close();

		return item;
	}


	
	public List<Items> findAllItems() {
		// TODO Auto-generated method stub
		return null;
	}

}
