package com.infosys.omsOrders.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.infosys.omsOrders.service.OrderService;
import com.infosys.omsOrders.dto.OrderDTO;
import com.infosys.omsOrders.dto.ProductsOrderedDTO;

@RestController
@CrossOrigin
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private Environment env;
	
	@GetMapping(value="/showorders/{buyerid:.+}")
	public ResponseEntity<List<OrderDTO>> showOrders(@PathVariable("buyerid") Integer buyerid) throws Exception{
		try {
			List<OrderDTO> list=orderService.getAllOrders(buyerid);
			return new ResponseEntity<List<OrderDTO>>(list,HttpStatus.OK);
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, env.getProperty(e.getMessage()));
		}
	}
	
	@GetMapping(value="/showsellerorders/{sellerid:.+}")
	public ResponseEntity<List<ProductsOrderedDTO>> showSellerOrders(@PathVariable("sellerid") Integer sellerid) throws Exception{
		try {
			List<ProductsOrderedDTO> list=orderService.getAllSellerOrders(sellerid);
			return new ResponseEntity<List<ProductsOrderedDTO>>(list,HttpStatus.OK);
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, env.getProperty(e.getMessage()));
		}
	}
	
	@PostMapping(value="/addorder",consumes=MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<String> addOrder(@RequestBody OrderDTO order) throws Exception{
		try
		{
			Integer n=orderService.addOrder(order);	
			String msg=env.getProperty("OrderController.ORDERED");
			return new ResponseEntity<String>(msg,HttpStatus.OK);
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, env.getProperty(e.getMessage()));
		}
	}
	
	@PostMapping(value="/reorder",consumes=MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<String> reOrder(@RequestBody OrderDTO order) throws Exception{
		try
		{
			Integer n=orderService.reOrder(order);
			String msg=env.getProperty("OrderController.REORDERED");
			return new ResponseEntity<String>(msg,HttpStatus.OK);
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, env.getProperty(e.getMessage()));
		}
	}
	
	@GetMapping(value="/removeorder/{id:.+}")
	public ResponseEntity<String> removeOrder(@PathVariable("id") Integer id) throws Exception{
		try
		{
			Integer n=orderService.removeOrder(id);
			String msg=env.getProperty("OrderController.ORDER_REMOVED");
			return new ResponseEntity<String>(msg,HttpStatus.OK);
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, env.getProperty(e.getMessage()));
		}
	}
	
	@GetMapping(value="/updatestatus/{orderid:.+}/{sellerid:.+}/{status:.+}")
	public ResponseEntity<String> updateStatus(@PathVariable("orderid") Integer orderid,@PathVariable("sellerid") 
	Integer sellerid, @PathVariable("status") String status) throws Exception{
		try
		{
			Integer n=orderService.updateStatus(orderid, sellerid, status);
			String msg=env.getProperty("OrderController.STATUS_UPDATED");
			return new ResponseEntity<String>(msg,HttpStatus.OK);
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, env.getProperty(e.getMessage()));
		}
	}
}
