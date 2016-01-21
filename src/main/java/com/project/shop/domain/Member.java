package com.project.shop.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * Member entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table
public class Member implements java.io.Serializable {

	// Fields
	@Id
	@Column(nullable=false,length=20)
	private String memberEmail;
	@Column(nullable=false,length=20)
	private String memberNickname;
	private Date memberBirth;
	@Column(nullable=false,length=10)
	private String memberLocation;
	@Column(nullable=false,length=20)
	private String memberPwd;
	@Column(nullable=false,length=20)
	private String memberpwdQuestion;
	@Column(nullable=false,length=20)
	private String memberPwdAnswer;
	private Integer memberRank;
	@Column(nullable=false,length=2)
	private String memberType;
	@Column(nullable=false,length=2)
	private String memberSex;
	private Date memberRegtime;
	private Double moneyLeft;
 	@OneToMany(mappedBy="member",fetch = FetchType.LAZY)
	private List<GoodsComment> goodsComments;
 	@OneToMany(mappedBy="member",fetch = FetchType.LAZY)
	private List<Reply> replies;
 	@OneToMany(mappedBy="member",fetch = FetchType.LAZY)
	private List<GoodsOrder> goodsOrders;

	// Constructors

	/** default constructor */
	public Member() {
		goodsComments=new ArrayList<GoodsComment>();
		replies=new ArrayList<Reply>();
		goodsOrders=new ArrayList<GoodsOrder>();
	}

	/** minimal constructor */
	public Member(String memberNickname, Date memberBirth,
			String memberLocation, String memberPwd, String memberpwdQuestion,
			String memberPwdAnswer, Integer memberRank, String memberType,
			Date memberRegtime) {
		this.memberNickname = memberNickname;
		this.memberBirth = memberBirth;
		this.memberLocation = memberLocation;
		this.memberPwd = memberPwd;
		this.memberpwdQuestion = memberpwdQuestion;
		this.memberPwdAnswer = memberPwdAnswer;
		this.memberRank = memberRank;
		this.memberType = memberType;
		this.memberRegtime = memberRegtime;
	}


	// Property accessors

	public String getMemberEmail() {
		return this.memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberNickname() {
		return this.memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public Date getMemberBirth() {
		return this.memberBirth;
	}

	public void setMemberBirth(Date memberBirth) {
		this.memberBirth = memberBirth;
	}

	public String getMemberLocation() {
		return this.memberLocation;
	}

	public void setMemberLocation(String memberLocation) {
		this.memberLocation = memberLocation;
	}

	public String getMemberPwd() {
		return this.memberPwd;
	}

	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}

	public String getMemberpwdQuestion() {
		return this.memberpwdQuestion;
	}

	public void setMemberpwdQuestion(String memberpwdQuestion) {
		this.memberpwdQuestion = memberpwdQuestion;
	}

	public String getMemberPwdAnswer() {
		return this.memberPwdAnswer;
	}

	public void setMemberPwdAnswer(String memberPwdAnswer) {
		this.memberPwdAnswer = memberPwdAnswer;
	}

	public Integer getMemberRank() {
		return this.memberRank;
	}

	public void setMemberRank(Integer memberRank) {
		this.memberRank = memberRank;
	}

	public String getMemberType() {
		return this.memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public Date getMemberRegtime() {
		return this.memberRegtime;
	}

	public void setMemberRegtime(Date memberRegtime) {
		this.memberRegtime = memberRegtime;
	}

	public List<GoodsComment> getGoodsComments() {
		return this.goodsComments;
	}

	public void setGoodsComments(List<GoodsComment> goodsComments) {
		this.goodsComments = goodsComments;
	}

	public List<Reply> getReplies() {
		return this.replies;
	}

	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}

	public List<GoodsOrder> getGoodsOrders() {
		return this.goodsOrders;
	}

	public void setGoodsOrders(List<GoodsOrder> goodsOrders) {
		this.goodsOrders = goodsOrders;
	}

	public String getMemberSex() {
		return memberSex;
	}

	public void setMemberSex(String memberSex) {
		this.memberSex = memberSex;
	}

	public Double getMoneyLeft() {
		return moneyLeft;
	}

	public void setMoneyLeft(Double moneyLeft) {
		this.moneyLeft = moneyLeft;
	}

}