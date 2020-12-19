package HDMRORDERS.PORTAL.CONTROLLER;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import HDMRORDERS.PORTAL.MODELS.OrderService;
import HDMRORDERS.PORTAL.MODELS.Orders;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderservice;
	
	@RequestMapping("/")
	public ModelAndView home() {
		List<Orders> listOrders = orderservice.listAll();
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("listOrders", listOrders);
		return mav;
	}
	
	@RequestMapping("/new")
	public String newCustomerForm(Map<String, Object> model) {
		Orders order = new Orders();
		model.put("order", order);
		return "new_order";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveOrder(@ModelAttribute("order") Orders order) {
		orderservice.save(order);
		return "redirect:/";
	}
	
	@RequestMapping("/edit")
	public ModelAndView editCustomerForm(@RequestParam long id) {
		ModelAndView mav = new ModelAndView("edit_order");
		Orders order = orderservice.get(id);
		mav.addObject("order", order);
		
		return mav;
	}
	
	@RequestMapping("/delete")
	public String deleteCustomerForm(@RequestParam long id) {
		orderservice.delete(id);
		return "redirect:/";		
	}
	
	@RequestMapping("/search")
	public ModelAndView search(@RequestParam String keyword) {
		List<Orders> result = orderservice.search(keyword);
		ModelAndView mav = new ModelAndView("search");
		mav.addObject("result", result);
		
		return mav;		
	}	
}
