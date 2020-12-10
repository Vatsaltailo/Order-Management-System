package com.infosys.omsOrders.dao;

import java.util.List;
import com.infosys.omsOrders.dto.OrderDTO;
import com.infosys.omsOrders.dto.ProductsOrderedDTO;

public interface OrderDAO {

	public List<OrderDTO> getAllOrders(Integer buyerid);
	public Integer addOrder(OrderDTO order);
	public Integer removeOrder(Integer id);
	public Integer reOrder(OrderDTO order);
	public List<ProductsOrderedDTO> getAllSellerOrders(Integer sellerid);
	public Integer updateStatus(Integer orderid, Integer sellerid, String status);
}
