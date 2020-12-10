package com.infosys.omsOrders.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.infosys.omsOrders.dto.OrderDTO;
import com.infosys.omsOrders.dto.ProductsOrderedDTO;
import com.infosys.omsOrders.entity.Order;
import com.infosys.omsOrders.entity.ProductsOrdered;

@Repository(value="orderDAO")
public class OrderDAOImpl implements OrderDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<OrderDTO> getAllOrders(Integer buyerid) {
		Query q=entityManager.createQuery("select o from Order o where o.buyerid = ?1");
		q.setParameter(1, buyerid);
		List<Order> orderlist=q.getResultList();
		List<OrderDTO> newList=new ArrayList<OrderDTO>();
		if(!orderlist.isEmpty())
		{	
			for(Order o:orderlist)
			{
				OrderDTO order=new OrderDTO();
				order.setOrderid(o.getOrderid());
				order.setBuyerid(o.getBuyerid());
				order.setAddress(o.getAddress());
				order.setAmount(o.getAmount());
				order.setDate(o.getDate());
				order.setStatus(o.getStatus());
				newList.add(order);
			}
		}
		return newList;
	}

	@Override
	public Integer addOrder(OrderDTO order) {
		Integer n=0;
		Double amount=0.0;
		Order o=new Order();
		o.setAddress(order.getAddress());
		amount=(order.getProduct().getPrice()*order.getProduct().getQuantity());
		o.setAmount(amount);
		o.setBuyerid(order.getBuyerid());
		o.setDate(order.getDate());
		o.setStatus(order.getStatus());
		entityManager.persist(o);
		n=o.getOrderid();
		ProductsOrdered p=new ProductsOrdered();
		p.setOrderid(n);
		p.setPrice(order.getProduct().getPrice());
		p.setProdid(order.getProduct().getProdid());
		p.setQuantity(order.getProduct().getQuantity());
		p.setSellerid(order.getProduct().getSellerid());
		p.setStatus(order.getProduct().getStatus());
		entityManager.persist(p);
		
		return n;
	}

	@Override
	public Integer removeOrder(Integer id) {
		Integer n=0;
		Order o=entityManager.find(Order.class,id);
		n=o.getOrderid();
		entityManager.remove(o);
		Query q=entityManager.createQuery("select p from ProductsOrdered p where p.orderid=?1");
		q.setParameter(1, id);
		List<ProductsOrdered> list=q.getResultList();
		for(ProductsOrdered p:list)
		{
			entityManager.remove(p);
		}
		return n;
	}

	@Override
	public Integer reOrder(OrderDTO order) {
		Integer n=0;
		Integer id=order.getOrderid();
		Order o=entityManager.find(Order.class, id);
		if(o.getOrderid()==order.getOrderid())
		{
			order.setOrderid(null);
			n=this.addOrder(order);
		}
		return n;
	}

	@Override
	public List<ProductsOrderedDTO> getAllSellerOrders(Integer sellerid) {
		Query q=entityManager.createQuery("select o from ProductsOrdered o where o.sellerid=?1");
		q.setParameter(1, sellerid);
		List<ProductsOrdered> prodOrdList=q.getResultList();
		List<ProductsOrderedDTO> list=new ArrayList<ProductsOrderedDTO>();
		if(!prodOrdList.isEmpty())
		{
			for(ProductsOrdered p:prodOrdList)
			{
				ProductsOrderedDTO prod=new ProductsOrderedDTO();
				prod.setOrderid(p.getOrderid());
				prod.setPrice(p.getPrice());
				prod.setProdid(p.getProdid());
				prod.setQuantity(p.getQuantity());
				prod.setSellerid(p.getSellerid());
				prod.setStatus(p.getStatus());
				list.add(prod);
			}
		}
		return list;
	}

	@Override
	public Integer updateStatus(Integer orderid, Integer sellerid, String status) {
		Integer n=0;
		Query q=entityManager.createQuery("update ProductsOrdered o set o.status=?1 where o.orderid=?2 and o.sellerid=?3");
		q.setParameter(1, status);
		q.setParameter(2, orderid);
		q.setParameter(3, sellerid);
		q.executeUpdate();
		
		Query q1=entityManager.createQuery("update Order o set o.status=?1 where o.orderid=?2");
		q1.setParameter(1, status);
		q1.setParameter(2, orderid);
		q1.executeUpdate();
		n=1;
		return n;
	}

}
