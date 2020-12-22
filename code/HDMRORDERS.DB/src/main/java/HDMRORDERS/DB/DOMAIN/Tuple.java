package HDMRORDERS.DB.DOMAIN;

public class Tuple {
	 public String orderNum;
	 public int item_num;
	 public double profit; 
	 public Tuple() {}
	 public Tuple(String orderNum, int item_num, Double double1) {
		this.orderNum = orderNum;
		this.item_num = item_num;
		this.profit = double1;
	 }
	 
}
