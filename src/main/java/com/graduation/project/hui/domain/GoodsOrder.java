package com.graduation.project.hui.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
 * GoodsOrder entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table
public class GoodsOrder implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue
	private Integer orderNo;
	@ManyToOne
	@JoinColumn(name="memberEmail")
	private Member member;
	@ManyToOne
	@JoinColumn(name="empNo")
	private Employee employee;
	@Column(nullable=false,length=20)
	private String orderRev;
	@Column(nullable=false,length=100)
	private String orderRevAddr;
	@Column(nullable=false,length=11)
	private String orderRevPhone;
	private Integer orderQuantity;
	private Double orderAmount;
	private Date orderTime;
	@Column(nullable=false,length=2)
	private String orderStatus;
	private Date timeLeft;
	@Column(length=20)
	private String logistics;
	@OneToMany(mappedBy="goodsOrder",fetch = FetchType.LAZY)
	private List<OrderItem> orderItems;

	// Constructors

	/** default constructor */
	public GoodsOrder() {
		orderItems=new ArrayList<OrderItem>();
	}

	/** minimal constructor */
	public GoodsOrder(Member member, String orderRev, String orderRevAddr,
			String orderRevPhone, Integer orderQuantity, Double orderAmount,
			Date orderTime, String orderStatus, Date timeLeft,String logistics) {
		this.member = member;
		this.orderRev = orderRev;
		this.orderRevAddr = orderRevAddr;
		this.orderRevPhone = orderRevPhone;
		this.orderQuantity = orderQuantity;
		this.orderAmount = orderAmount;
		this.orderTime = orderTime;
		this.orderStatus = orderStatus;
		this.timeLeft = timeLeft;
		this.logistics = logistics;
	}

	// Property accessors

	public Integer getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getOrderRev() {
		return this.orderRev;
	}

	public void setOrderRev(String orderRev) {
		this.orderRev = orderRev;
	}

	public String getOrderRevAddr() {
		return this.orderRevAddr;
	}

	public void setOrderRevAddr(String orderRevAddr) {
		this.orderRevAddr = orderRevAddr;
	}

	public String getOrderRevPhone() {
		return this.orderRevPhone;
	}

	public void setOrderRevPhone(String orderRevPhone) {
		this.orderRevPhone = orderRevPhone;
	}

	public Integer getOrderQuantity() {
		return this.orderQuantity;
	}

	public void setOrderQuantity(Integer orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public Double getOrderAmount() {
		return this.orderAmount;
	}

	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Date getOrderTime() {
		return this.orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public String getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getTimeLeft() {
		return this.timeLeft;
	}

	public void setTimeLeft(Date timeLeft) {
		this.timeLeft = timeLeft;
	}

	public List<OrderItem> getOrderItems() {
		return this.orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public String getLogistics() {
		return logistics;
	}

	public void setLogistics(String logistics) {
		this.logistics = logistics;
	}


}