package HDMRORDERS.PORTAL.MODELS;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends CrudRepository<Orders, Long> {
	@Query(value = "SELECT c FROM orders c WHERE c.order_num LIKE '%' || :keyword || '%'"
	/*
	 * + " OR c.email LIKE '%' || :keyword || '%'" +
	 * " OR c.address LIKE '%' || :keyword || '%'"
	 */
	)
	public List<Orders> search(@Param("keyword") String keyword);
}
