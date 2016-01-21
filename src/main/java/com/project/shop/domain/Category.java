package com.project.shop.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



/**
 * Category entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table
public class Category implements java.io.Serializable {

	// Fields
	@Id
	@Column(length=10)/*
	@GeneratedValue(generator = "paymentableGenerator")    
    @GenericGenerator(name = "paymentableGenerator", strategy = "assigned")*/
	private String ctgNo;
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })  
	@JoinColumn(name="empNo")
	private Employee employee;
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name="pareCtgNo")
	private Category category;
	@Column(nullable=false,length=20)
	private String ctgName;
	private Integer ctgAmount;
	private String ctgMemo;
	private Date ctgCreateTime;
	@OneToMany(mappedBy="category",cascade = CascadeType.ALL,fetch = FetchType.LAZY )
	private List<Goods> goodses ;
    @OneToMany(mappedBy="category",cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
	private List<Category> categories ;

	// Constructors

	/** default constructor */
	public Category() {
		goodses=new ArrayList<Goods>();
		categories=new ArrayList<Category>();
	}

	/** minimal constructor */
	public Category(Employee employee, String ctgName, Integer ctgAmount,
			String ctgMemo, Date ctgCreateTime) {
		this.employee = employee;
		this.ctgName = ctgName;
		this.ctgAmount = ctgAmount;
		this.ctgMemo = ctgMemo;
		this.ctgCreateTime = ctgCreateTime;
	}



	// Property accessors

	public String getCtgNo() {
		return this.ctgNo;
	}

	public void setCtgNo(String ctgNo) {
		this.ctgNo = ctgNo;
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

	public String getCtgName() {
		return this.ctgName;
	}

	public void setCtgName(String ctgName) {
		this.ctgName = ctgName;
	}

	public Integer getCtgAmount() {
		return this.ctgAmount;
	}

	public void setCtgAmount(Integer ctgAmount) {
		this.ctgAmount = ctgAmount;
	}

	public String getCtgMemo() {
		return this.ctgMemo;
	}

	public void setCtgMemo(String ctgMemo) {
		this.ctgMemo = ctgMemo;
	}

	public Date getCtgCreateTime() {
		return this.ctgCreateTime;
	}

	public void setCtgCreateTime(Date ctgCreateTime) {
		this.ctgCreateTime = ctgCreateTime;
	}
/*
	public Set<Goods> getGoodses() {
		return goodses;
	}

	public void setGoodses(Set<Goods> goodses) {
		this.goodses = goodses;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
*/
	public List<Goods> getGoodses() {
		return this.goodses;
	}

	public void setGoodses(List<Goods> goodses) {
		this.goodses = goodses;
	}

	public List<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

}