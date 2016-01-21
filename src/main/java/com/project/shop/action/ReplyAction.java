package com.project.shop.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.project.shop.domain.Goods;
import com.project.shop.domain.GoodsComment;
import com.project.shop.domain.Member;
import com.project.shop.domain.Reply;
import com.project.shop.service.GoodsCommentService;
import com.project.shop.service.ReplyService;
import com.project.shop.service.impl.GoodsCommentServiceImpl;
import com.project.shop.service.impl.ReplyServiceImpl;
@Controller
@Scope("prototype")
@ParentPackage("reply")

@InterceptorRefs( {
	@InterceptorRef(value = "loginStack") })
public class ReplyAction
{
	private Reply reply; 
	private Goods goods;
	public Reply getReply() {
		return reply;
	}

	public void setReply(Reply reply) {
		this.reply = reply;
	}
	
	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	@Action(value = "replyAction")
	public String addEntity() {
		HttpServletRequest request = ServletActionContext.getRequest();
		ReplyService replyService=new ReplyServiceImpl();
		Reply rep=new Reply(); 
		
		GoodsComment comment=new GoodsComment();
		comment.setCommentNo(Integer.parseInt(request.getParameter("commentNo")));
		GoodsCommentService goodsCommentService=new GoodsCommentServiceImpl();
	    comment=goodsCommentService.getEntity(comment.getCommentNo());
	    rep.setGoodsComment(comment);
	    if(comment.getReplyAmount()==15){
	    	
	    }
	    else{
		Member member=new Member();		
		member=(Member) request.getSession().getAttribute("loginMember");
		if(member==null){
			return "login";
		}
		else{
		rep.setMember(member);
		
		rep.setReplyTxt(request.getParameter("estimate"+request.getParameter("commentNo")));
		rep.setReplyTime(new Date());
		replyService.addEntity(rep);

	    
	    comment.setReplyAmount(comment.getReplyAmount()+1);
	    goodsCommentService.uptEntity(comment);
		}
	    }
        if(request.getParameter("next")!=null&&request.getParameter("next").equals("reply")){
        	reply=rep;
        	return "reply";
        }
		return "detail";
	}

	public String delEntity() {
		ReplyService replyService=new ReplyServiceImpl();
		replyService.delEntity(reply.getReplyNo());
		return "success";
	}

	public String getEntity() {
		ReplyService replyService=new ReplyServiceImpl();
		replyService.getEntity(reply.getReplyNo());
		return "success";
	}

	public String uptEntity() {
		ReplyService replyService=new ReplyServiceImpl();
		replyService.uptEntity(reply);
		return "success";
	}


}