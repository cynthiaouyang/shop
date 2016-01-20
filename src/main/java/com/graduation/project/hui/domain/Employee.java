package com.graduation.project.hui.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * Employee entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table
public class Employee implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue
	private Integer empNo;
	@Column(nullable=false,length=20)
	private String empPwd;
	@Column(nullable=false,length=2)
	private String empType;
	@Column(nullable=false,length=20)
	private String empName;
	@Column(nullable=false,length=1)
	private String empSex;
	private Date empRegdate;
	@OneToMany(mappedBy="employee",fetch = FetchType.LAZY)
	private List<Category> categories;
 	@OneToMany(mappedBy="employee",fetch = FetchType.LAZY)
	private List<GoodsOrder> goodsOrders;
 	@OneToMany(mappedBy="employee",fetch = FetchType.LAZY)
	private List<Goods> goodses;

	// Constructors

	/** default constructor */
	public Employee() {
		categories=new ArrayList<Category>();
		goodsOrders=new ArrayList<GoodsOrder>();
		goodses=new ArrayList<Goods>();
	}

	/** minimal constructor */
	public Employee(String empPwd, String empType, String empName,
			String empSex, Date empRegdate) {
		this.empPwd = empPwd;
		this.empType = empType;
		this.empName = empName;
		this.empSex = empSex;
		this.empRegdate = empRegdate;
	}


	// Property accessors

	public Integer getEmpNo() {
		return this.empNo;
	}

	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}

	public String getEmpPwd() {
		return this.empPwd;
	}

	public void setEmpPwd(String empPwd) {
		this.empPwd = empPwd;
	}

	public String getEmpType() {
		return this.empType;
	}

	public void setEmpType(String empType) {
		this.empType = empType;
	}

	public String getEmpName() {
		return this.empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpSex() {
		return this.empSex;
	}

	public void setEmpSex(String empSex) {
		this.empSex = empSex;
	}

	public Date getEmpRegdate() {
		return empRegdate;
	}

	public void setEmpRegdate(Date empRegdate) {
		this.empRegdate = empRegdate;
	}

	public List<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<GoodsOrder> getGoodsOrders() {
		return this.goodsOrders;
	}

	public void setGoodsOrders(List<GoodsOrder> goodsOrders) {
		this.goodsOrders = goodsOrders;
	}

	public List<Goods> getGoodses() {
		return this.goodses;
	}

	public void setGoodses(List<Goods> goodses) {
		this.goodses = goodses;
	}

}