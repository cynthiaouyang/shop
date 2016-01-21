package com.project.shop.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Category entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table
public class AddrBook implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue
	private Integer bookNo;
 	@ManyToOne
 	@JoinColumn(name = "memberEmail")
	private Member member;
	@Column(nullable=false,length=20)
	private String rev;
	@Column(nullable=false,length=100)
	private String revAddr;
	@Column(nullable=false,length=11)
	private String revPhone;
	private Date createtime;

	public AddrBook() {
		super();
	}

	public AddrBook(Integer bookNo, Member member, String rev, String revAddr,
			String revPhone, Date createtime) {
		super();
		this.bookNo = bookNo;
		this.member = member;
		this.rev = rev;
		this.revAddr = revAddr;
		this.revPhone = revPhone;
		this.createtime = createtime;
	}

	public Integer getBookNo() {
		return bookNo;
	}

	public void setBookNo(Integer bookNo) {
		this.bookNo = bookNo;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getRev() {
		return rev;
	}

	public void setRev(String rev) {
		this.rev = rev;
	}

	public String getRevAddr() {
		return revAddr;
	}

	public void setRevAddr(String revAddr) {
		this.revAddr = revAddr;
	}

	public String getRevPhone() {
		return revPhone;
	}

	public void setRevPhone(String revPhone) {
		this.revPhone = revPhone;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}