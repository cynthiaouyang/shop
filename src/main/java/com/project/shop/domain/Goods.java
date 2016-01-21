package com.project.shop.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Goods entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table
public class Goods implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue
	private Integer goodsNo;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="empNo")
	private Employee employee;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ctgNo")
	private Category category;
	@Column(nullable=false,length=20)
	private String goodsShortName;
	@Column(nullable=false,length=50)
	private String goodsName;
	private Double goodsPrice;
	@Column(nullable=false,length=20)
	private String goodsSize;
	@Column(nullable=false,length=20)
	private String goodsColor;
	@Column(nullable=false,length=2)
	private String goodsStatus;
	@Column(nullable=false,length=250)
	private String goodsDesc;
	private Integer soldAmount;
	@Column(columnDefinition="decimal(2,1)")
	private Float goodsMark;
	@Column(nullable=false,length=100)
	private String goodsPics;
	private Date goodsRegtime;
 	@OneToMany(mappedBy="goods",fetch = FetchType.LAZY)
	private List<OrderItem> orderItems;
 	@OneToMany(mappedBy="goods",fetch = FetchType.LAZY)
	private List<GoodsComment> goodsComments;



 	public Goods() {
		orderItems=new ArrayList<OrderItem>();
		goodsComments=new ArrayList<GoodsComment>();
	}


 	
	public Goods(Integer goodsNo, Employee employee, Category category,
			String goodsShortName, String goodsName, Double goodsPrice,
			String goodsSize, String goodsColor, String goodsStatus,
			String goodsDesc, Integer soldAmount, Float goodsMark,
			String goodsPics, Date goodsRegtime) {
		super();
		this.goodsNo = goodsNo;
		this.employee = employee;
		this.category = category;
		this.goodsShortName = goodsShortName;
		this.goodsName = goodsName;
		this.goodsPrice = goodsPrice;
		this.goodsSize = goodsSize;
		this.goodsColor = goodsColor;
		this.goodsStatus = goodsStatus;
		this.goodsDesc = goodsDesc;
		this.soldAmount = soldAmount;
		this.goodsMark = goodsMark;
		this.goodsPics = goodsPics;
		this.goodsRegtime = goodsRegtime;
	}

	// Property accessors

	public Integer getGoodsNo() {
		return this.goodsNo;
	}

	public void setGoodsNo(Integer goodsNo) {
		this.goodsNo = goodsNo;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	

	public Double getGoodsPrice() {
		return this.goodsPrice;
	}

	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public String getGoodsSize() {
		return this.goodsSize;
	}

	public void setGoodsSize(String goodsSize) {
		this.goodsSize = goodsSize;
	}

	public String getGoodsColor() {
		return this.goodsColor;
	}

	public void setGoodsColor(String goodsColor) {
		this.goodsColor = goodsColor;
	}

	public String getGoodsStatus() {
		return this.goodsStatus;
	}

	public void setGoodsStatus(String goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	public String getGoodsDesc() {
		return this.goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}



	public String getGoodsShortName() {
		return goodsShortName;
	}

	public void setGoodsShortName(String goodsShortName) {
		this.goodsShortName = goodsShortName;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	

	public Integer getSoldAmount() {
		return soldAmount;
	}



	public void setSoldAmount(Integer soldAmount) {
		this.soldAmount = soldAmount;
	}



	public Float getGoodsMark() {
		return goodsMark;
	}

	public void setGoodsMark(Float goodsMark) {
		this.goodsMark = goodsMark;
	}

	public String getGoodsPics() {
		return this.goodsPics;
	}

	public void setGoodsPics(String goodsPics) {
		this.goodsPics = goodsPics;
	}

	public Date getGoodsRegtime() {
		return this.goodsRegtime;
	}

	public void setGoodsRegtime(Date goodsRegtime) {
		this.goodsRegtime = goodsRegtime;
	}
	@OneToMany(mappedBy="itemNo",targetEntity=OrderItem.class)
	public List<OrderItem> getOrderItems() {
		return this.orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	@OneToMany(mappedBy="commentNo",targetEntity=GoodsComment.class)
	public List<GoodsComment> getGoodsComments() {
		return this.goodsComments;
	}

	public void setGoodsComments(List<GoodsComment> goodsComments) {
		this.goodsComments = goodsComments;
	}

}