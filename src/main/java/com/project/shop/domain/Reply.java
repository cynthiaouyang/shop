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
 * Reply entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table
public class Reply implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue
	private Integer replyNo;
	@ManyToOne
	@JoinColumn(name="memberEmail")
	private Member member;
	@ManyToOne
	@JoinColumn(name="commentNo")
	private GoodsComment goodsComment;
	@Column(nullable=false,length=150)
	private String replyTxt;
	private Date replyTime;

	// Constructors

	/** default constructor */
	public Reply() {
	}

	/** minimal constructor */
	public Reply(String replyTxt, Date replyTime) {
		this.replyTxt = replyTxt;
		this.replyTime = replyTime;
	}

	/** full constructor */
	public Reply(Member member, GoodsComment goodsComment, String replyTxt,
			Date replyTime) {
		this.member = member;
		this.goodsComment = goodsComment;
		this.replyTxt = replyTxt;
		this.replyTime = replyTime;
	}

	// Property accessors

	public Integer getReplyNo() {
		return this.replyNo;
	}

	public void setReplyNo(Integer replyNo) {
		this.replyNo = replyNo;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public GoodsComment getGoodsComment() {
		return this.goodsComment;
	}

	public void setGoodsComment(GoodsComment goodsComment) {
		this.goodsComment = goodsComment;
	}

	public String getReplyTxt() {
		return this.replyTxt;
	}

	public void setReplyTxt(String replyTxt) {
		this.replyTxt = replyTxt;
	}

	public Date getReplyTime() {
		return this.replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

}