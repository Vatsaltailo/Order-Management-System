package com.infosys.omsOrders.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infosys.omsOrders.dao.OrderDAO;
import com.infosys.omsOrders.dto.OrderDTO;
import com.infosys.omsOrders.dto.ProductsOrderedDTO;


@Transactional
@Service(value="orderService")
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDAO orderDAO;
	
	@Override
	public List<OrderDTO> getAllOrders(Integer buyerid) throws Exception {
		List<OrderDTO> list=orderDAO.getAllOrders(buyerid);
		if(list.isEmpty())
		{
			throw new Exception("OrderService.NO_ORDERS");
		}
		return list;
	}

	@Override
	public Integer addOrder(OrderDTO order) throws Exception {
		Integer n=orderDAO.addOrder(order);
		if(n==0)
		{
			throw new Exception("OrderService.ORDER_NOT_ADDED");
		}
		return n;
	}
	//needs checking
	@Override
	public Integer removeOrder(Integer id) throws Exception {
		Integer oid=orderDAO.removeOrder(id);
		if(oid==0)
		{
			throw new Exception("OrderService.ORDER_NOT_FOUND");
		}
		return oid;
	}

	@Override
	public Integer reOrder(OrderDTO order) throws Exception {
		Integer n=orderDAO.reOrder(order);
		if(n==0)
		{
			throw new Exception("OrderService.NOT_REORDERED");
		}
		return n;
	}

	@Override
	public List<ProductsOrderedDTO> getAllSellerOrders(Integer sellerid) throws Exception {
		List<ProductsOrderedDTO> list=orderDAO.getAllSellerOrders(sellerid);
		if(list.isEmpty())
		{
			throw new Exception("OrderService.NO_ORDERS_FROM_YOUR_PRODUCTS");
		}
		return list;
	}

	@Override
	public Integer updateStatus(Integer orderid, Integer sellerid, String status) throws Exception {
		Integer n=orderDAO.updateStatus(orderid, sellerid, status);
		if(n==0)
		{
			throw new Exception("OrderService.NO_SUCH_ORDER");
		}
		return n;
	}

}
