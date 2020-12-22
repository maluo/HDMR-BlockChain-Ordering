package HDMRORDERS.DB.DAO;

import java.util.List;

import HDMRORDERS.DB.DOMAIN.Items;

public interface ItemDao {
	
	long saveItem(Items order);

	void updateItem(Items order);

	void deleteItem(Integer id);

	Items findItemById(Integer id);

	List<Items> findAllItems();

}