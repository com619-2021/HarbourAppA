
package HmController;

import dto.Order;
import dto.OrderStatus;


import dto.Pilot;
import service.PilotService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import service.OrderService;

@RestController
public class OrderController {
	private transient final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory
		.getLog(this.getClass());

	@Autowired
	OrderService orderService;

	@GetMapping(value = "/api/order/find")
	@ResponseBody
	public ResponseEntity<Object> findOrder(@RequestParam int id) {
		log.info("/api/order: entered.");
		log.info("/api/order: order #" + id + " requested.");

		Order order = orderService.retrieveOrder(id);

		if (order == null) {
			return new ResponseEntity<>(
										String.format("ERROR: Order #%d not found. This order may not exist in the database.", id),
										HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(order, HttpStatus.OK);
		}
	}

	@GetMapping(value = "/api/order/cancel")
	@ResponseBody
	public ResponseEntity<Object> cancelOrder(@RequestParam int id) {
		log.info("/api/order: entered.");
		log.info("/api/order: cancellation of order #" + id + " requested.");

		if (orderService.cancelOrder(id)) {
			return new ResponseEntity<>(String.format("Order #%d has been set to %s.", id, OrderStatus.CANCELLED.name()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(String.format("ERROR: Order #%d not found. This order may not exist in the database.", id), HttpStatus.NOT_FOUND);
		}
	}
}
