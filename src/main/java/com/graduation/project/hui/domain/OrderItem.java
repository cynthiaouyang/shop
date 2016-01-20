package com.graduation.project.hui.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * OrderItem entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table
public class OrderItem implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue
	private Integer itemNo;
	@ManyToOne
	@JoinColumn(name="orderNo")
	private GoodsOrder goodsOrder;
	@ManyToOne
	@JoinColumn(name="goodsNo")
	private Goods goods;
	@Column(nullable=false,length=10)
	private String itemSize;
	@Column(nullable=false,length=10)
	private String itemColor;
	private Integer itemQuantity;
	private Double itemAmount;

	// Constructors

	/** default constructor */
	public OrderItem() {
	}

	/** full constructor */
	public OrderItem(GoodsOrder goodsOrder, Goods goods, String itemSize,
			String itemColor, Integer itemQuantity, Double itemAmount) {
		this.goodsOrder = goodsOrder;
		this.goods = goods;
		this.itemSize = itemSize;
		this.itemColor = itemColor;
		this.itemQuantity = itemQuantity;
		this.itemAmount = itemAmount;
	}

	// Property accessors

	public Integer getItemNo() {
		return this.itemNo;
	}

	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}

	public GoodsOrder getGoodsOrder() {
		return this.goodsOrder;
	}

	public void setGoodsOrder(GoodsOrder goodsOrder) {
		this.goodsOrder = goodsOrder;
	}

	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public String getItemSize() {
		return this.itemSize;
	}

	public void setItemSize(String itemSize) {
		this.itemSize = itemSize;
	}

	public String getItemColor() {
		return this.itemColor;
	}

	public void setItemColor(String itemColor) {
		this.itemColor = itemColor;
	}

	public Integer getItemQuantity() {
		return this.itemQuantity;
	}

	public void setItemQuantity(Integer itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public Double getItemAmount() {
		return this.itemAmount;
	}

	public void setItemAmount(Double itemAmount) {
		this.itemAmount = itemAmount;
	}

}