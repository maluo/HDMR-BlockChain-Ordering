package HDMRORDERS.PORTAL4.DAO;

import java.util.List;

import HDMRORDERS.PORTAL4.DOMAIN.Customer;

public interface CustomerDao {
	Long saveCustomer(Customer customer);

	void updateCustomer(Customer customer);

	void deleteCustomer(Long id);

	Customer findCustomerById(Long id);

	List<Customer> findAllCustomers();
}
