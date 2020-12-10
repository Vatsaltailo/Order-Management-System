package com.infosys.omsOrders.service;

import java.util.List;
import com.infosys.omsOrders.dto.OrderDTO;
import com.infosys.omsOrders.dto.ProductsOrderedDTO;

public interface OrderService {
		
		public List<OrderDTO> getAllOrders(Integer buyerid) throws Exception;
		public Integer addOrder(OrderDTO order) throws Exception;
		public Integer removeOrder(Integer id) throws Exception;
		public Integer reOrder(OrderDTO order) throws Exception;
		public List<ProductsOrderedDTO> getAllSellerOrders(Integer sellerid) throws Exception;
		public Integer updateStatus(Integer orderid, Integer prodid, String status) throws Exception;


}
