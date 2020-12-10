package com.infosys.omsOrders.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="productsordered")
@IdClass(ProductsOrdered.class)
public class ProductsOrdered implements Serializable{
	
	@Id
	@Column(name="orderid",nullable=false)
	private Integer orderid;
	
	@Id
	@Column(name="prodid",nullable=false)
	private Integer prodid;
	
	@Column(name="sellerid")
	private Integer sellerid;
	
	@Column(name="quantity")
	private Integer quantity;
	
	@Column(name="status")
	private String status;
	
	@Column(name="price")
	private Double price;
	
	public Integer getOrderid() {
		return orderid;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	public Integer getProdid() {
		return prodid;
	}
	public void setProdid(Integer prodid) {
		this.prodid = prodid;
	}
	public Integer getSellerid() {
		return sellerid;
	}
	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
}
