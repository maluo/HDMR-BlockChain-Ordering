package HDMRORDERS.PORTAL4.DAO.IMPL;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import HDMRORDERS.PORTAL4.DAO.ItemDao;
import HDMRORDERS.PORTAL4.DOMAIN.Items;
import HDMRORDERS.PORTAL4.UTIL.HibernateUtil;

public class ItemDaoIMPL implements ItemDao{

	private static ItemDaoIMPL itemDaoImpl = null;
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	
    public static ItemDao getInstance() {
        if(itemDaoImpl == null)
        	itemDaoImpl = new ItemDaoIMPL();
         
        return itemDaoImpl;
    }


	@Override
	public long saveItem(Items order) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void updateItem(Items order) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteItem(Integer id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Items findItemById(Integer id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		Items item = session.get(Items.class, id);
		session.close();

		return item;
	}


	@Override
	public List<Items> findAllItems() {
		// TODO Auto-generated method stub
		return null;
	}

}
