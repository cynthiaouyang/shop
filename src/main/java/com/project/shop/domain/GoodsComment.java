package com.project.shop.domain;

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
 * GoodsComment entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table
public class GoodsComment implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue
	private Integer commentNo;
	@ManyToOne
	@JoinColumn(name="memberEmail")
	private Member member;
	@ManyToOne
	@JoinColumn(name="goodsNo")
	private Goods goods;
	private Float goodsMark;
	@Column(nullable=false,length=150)
	private String commentContent;
	private Date commentTime;
	private Integer replyAmount;
 	@OneToMany(mappedBy="goodsComment",fetch = FetchType.LAZY)
	private List<Reply> replies;

	// Constructors

	/** default constructor */
	public GoodsComment() {
		replies=new ArrayList<Reply>();
	}

	/** minimal constructor */
	public GoodsComment(Member member, Goods goods,
			Float goodsMark, String commentContent, Date commentTime,
			Integer replyAmount) {
		this.member = member;
		this.goods = goods;
		this.goodsMark = goodsMark;
		this.commentContent = commentContent;
		this.commentTime = commentTime;
		this.replyAmount = replyAmount;
	}

	// Property accessors

	public Integer getCommentNo() {
		return this.commentNo;
	}

	public void setCommentNo(Integer commentNo) {
		this.commentNo = commentNo;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Float getGoodsMark() {
		return this.goodsMark;
	}

	public void setGoodsMark(Float goodsMark) {
		this.goodsMark = goodsMark;
	}

	public String getCommentContent() {
		return this.commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Date getCommentTime() {
		return this.commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	public Integer getReplyAmount() {
		return this.replyAmount;
	}

	public void setReplyAmount(Integer replyAmount) {
		this.replyAmount = replyAmount;
	}

	public List<Reply> getReplies() {
		return this.replies;
	}

	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}

}