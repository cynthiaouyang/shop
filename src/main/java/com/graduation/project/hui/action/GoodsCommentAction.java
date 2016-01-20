package com.graduation.project.hui.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.graduation.project.hui.domain.Goods;
import com.graduation.project.hui.domain.GoodsComment;
import com.graduation.project.hui.domain.GoodsOrder;
import com.graduation.project.hui.domain.Member;
import com.graduation.project.hui.domain.OrderItem;
import com.graduation.project.hui.domain.Reply;
import com.graduation.project.hui.service.GoodsCommentService;
import com.graduation.project.hui.service.GoodsOrderService;
import com.graduation.project.hui.service.GoodsService;
import com.graduation.project.hui.service.MemberService;
import com.graduation.project.hui.service.OrderItemService;
import com.graduation.project.hui.service.ReplyService;
import com.graduation.project.hui.service.impl.GoodsCommentServiceImpl;
import com.graduation.project.hui.service.impl.GoodsOrderServiceImpl;
import com.graduation.project.hui.service.impl.GoodsServiceImpl;
import com.graduation.project.hui.service.impl.MemberServiceImpl;
import com.graduation.project.hui.service.impl.OrderItemServiceImpl;
import com.graduation.project.hui.service.impl.ReplyServiceImpl;


@Controller
@Scope("prototype")
@ParentPackage("goodsComment")
@InterceptorRefs( {
	@InterceptorRef(value = "loginStack") })
public class GoodsCommentAction 
 {
	
	private GoodsComment goodsComment; 

	
	public GoodsComment getGoodsComment() {
		return goodsComment;
	}

	public void setGoodsComment(GoodsComment goodsComment) {
		this.goodsComment = goodsComment;
	}


	@Action(value = "commentAction")
	public String addEntity() {
	    HttpServletRequest request = ServletActionContext.getRequest();
		GoodsOrderService goodsOrderService=new GoodsOrderServiceImpl();
		OrderItemService orderItemService=new OrderItemServiceImpl();

		
		int i=0;
		Member member=new Member();			
		member=(Member) request.getSession().getAttribute("loginMember");

		GoodsOrder goodsOrder=new GoodsOrder();
		goodsOrder.setOrderNo(Integer.parseInt(request.getParameter("orderNo")));
		List<OrderItem> items=new ArrayList<OrderItem>();
		OrderItem orderItem=new OrderItem();
		orderItem.setGoodsOrder(goodsOrder);
		items=orderItemService.queryEntity(orderItem);	
		for(OrderItem item:items){			
		    GoodsCommentService goodsCommentService=new GoodsCommentServiceImpl();
			GoodsComment comment=new GoodsComment();
			comment.setCommentContent(request.getParameter("eatimate"+i));
			comment.setCommentTime(new Date());
			comment.setGoodsMark(Float.parseFloat(request.getParameter("mark"+i)));
			comment.setMember(member);
			comment.setReplyAmount(0);
			Goods goods=new Goods();
			goods.setGoodsNo(item.getGoods().getGoodsNo());
			comment.setGoods(goods);

			//System.out.println("==================="+i);
			goodsCommentService.addEntity(comment);
			goods=item.getGoods();
			goods.setGoodsMark((goods.getSoldAmount()*goods.getGoodsMark()+Float.parseFloat(request.getParameter("mark"+i)))/(goods.getSoldAmount()+1));
			goods.setSoldAmount(goods.getSoldAmount()+1);
			//System.out.println("--------------------------"+i);
			
			GoodsService goodsService=new GoodsServiceImpl();
			goodsService.mergeEntity(goods);
			
			
			//System.out.println("+++++++++++++++++++++"+i);
			
		}
		goodsOrder=goodsOrderService.getEntity(Integer.parseInt(request.getParameter("orderNo")));		
		goodsOrder.setOrderItems(items);
		goodsOrder.setOrderStatus("3");
	//	System.out.println("=====================OrderAmount:"+goodsOrder.getOrderAmount());
		member=(Member) request.getSession().getAttribute("loginMember");
	//	System.out.println("=====================MemberRank:"+member.getMemberRank());
		
		member.setMemberRank(member.getMemberRank()+goodsOrder.getOrderAmount().intValue());
	    if(member.getMemberRank()>=1000)
	    	member.setMemberType("1");
	    else if(member.getMemberRank()>=4000)
	    	member.setMemberType("2");
	    else if(member.getMemberRank()>=10000)
	    	member.setMemberType("3");
	    else if(member.getMemberRank()>=40000)
	    	member.setMemberType("4");
	    else if(member.getMemberRank()>=100000)
	    	member.setMemberType("5");
		MemberService  memberService=new MemberServiceImpl();
		memberService.uptEntity(member);
		request.getSession().setAttribute("loginMember",member);
		goodsOrderService.uptEntity(goodsOrder);
		return "message";
	}

	public String delEntity() {
		GoodsCommentService goodsCommentService=new GoodsCommentServiceImpl();
		goodsCommentService.delEntity(goodsComment.getCommentNo());
		return "success";
	}

	public String getReply() {
		GoodsCommentService goodsCommentService=new GoodsCommentServiceImpl();
		ReplyService replyService=new ReplyServiceImpl();
	    HttpServletRequest request = ServletActionContext.getRequest();
		goodsComment=goodsCommentService.getEntity(Integer.parseInt(request.getParameter("commentNo")));
		Reply reply=new Reply();
		reply.setGoodsComment(goodsComment);
		List<Reply> replies=new ArrayList<Reply>();
		replies=replyService.queryEntity(reply);
		goodsComment.setReplies(replies);
		return "reply";
	}

	public String uptEntity() {
		GoodsCommentService goodsCommentService=new GoodsCommentServiceImpl();
		goodsCommentService.uptEntity(goodsComment);
		return "success";
	}

	

	
	
 }