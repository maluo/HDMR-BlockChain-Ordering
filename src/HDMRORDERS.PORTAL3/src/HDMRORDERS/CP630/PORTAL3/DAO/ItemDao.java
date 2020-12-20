package HDMRORDERS.CP630.PORTAL3.DAO;

import java.util.List;

import HDMRORDERS.CP630.PORTAL3.DOMAIN.Items;

public interface ItemDao {
	
	long saveItem(Items order);

	void updateItem(Items order);

	void deleteItem(Integer id);

	Items findItemById(Integer id);

	List<Items> findAllItems();

}