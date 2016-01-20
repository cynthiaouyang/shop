package com.graduation.project.hui.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.graduation.project.hui.domain.AddrBook;
import com.graduation.project.hui.domain.Employee;
import com.graduation.project.hui.domain.GoodsComment;
import com.graduation.project.hui.domain.GoodsOrder;
import com.graduation.project.hui.domain.Member;
import com.graduation.project.hui.domain.OrderItem;
import com.graduation.project.hui.service.AddrBookService;
import com.graduation.project.hui.service.GoodsCommentService;
import com.graduation.project.hui.service.GoodsOrderService;
import com.graduation.project.hui.service.MemberService;
import com.graduation.project.hui.service.OrderItemService;
import com.graduation.project.hui.service.impl.AddrBookServiceImpl;
import com.graduation.project.hui.service.impl.GoodsCommentServiceImpl;
import com.graduation.project.hui.service.impl.GoodsOrderServiceImpl;
import com.graduation.project.hui.service.impl.MemberServiceImpl;
import com.graduation.project.hui.service.impl.OrderItemServiceImpl;
import com.graduation.project.hui.utils.Page;

@Controller
@Scope("prototype")
@ParentPackage("goodsOrder")
@InterceptorRefs({ @InterceptorRef(value = "loginStack") })
public class GoodsOrderAction {

	private GoodsOrder goodsOrder;
	private GoodsOrder orderHelper;
	private AddrBook addrBook;
	private String frontPage;
	private Page page;

	public GoodsOrder getGoodsOrder() {
		return goodsOrder;
	}

	public void setGoodsOrder(GoodsOrder goodsOrder) {
		this.goodsOrder = goodsOrder;
	}

	public GoodsOrder getOrderHelper() {
		return orderHelper;
	}

	public void setOrderHelper(GoodsOrder orderHelper) {
		this.orderHelper = orderHelper;
	}

	public String getFrontPage() {
		return frontPage;
	}

	public void setFrontPage(String frontPage) {
		this.frontPage = frontPage;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public AddrBook getAddrBook() {
		return addrBook;
	}

	public void setAddrBook(AddrBook addrBook) {
		this.addrBook = addrBook;
	}

	@Action(value = "orderAction")
	public String pay() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Member member = new Member();
		member = (Member) request.getSession().getAttribute("loginMember");
		if (member == null) {
			frontPage = "pay";
			return "login";
		} else {
			return "payPage";
		}

	}

	public String myMessage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Member member = new Member();
		member = (Member) request.getSession().getAttribute("loginMember");
		if (member == null) {
			frontPage = "message";
			return "login";
		} else {
			if (StringUtils.isNotEmpty(request.getParameter("pageNo")))
				page = new Page(
						Integer.parseInt(request.getParameter("pageNo")));
			else
				page = new Page();
			/*
			 * GoodsOrder order=new GoodsOrder(); order.setMember(member);
			 */
			if (orderHelper == null) {
				orderHelper = new GoodsOrder();
				orderHelper.setMember(member);
			}
			GoodsOrderService goodsOrderService = new GoodsOrderServiceImpl();
			page = goodsOrderService.getList(page, orderHelper);
			getTimeLeft();
			request.setAttribute("msgActive", "acc-menu-active");
			return "message";
		}
	}

	public String addEntity() {
		GoodsOrderService goodsOrderService = new GoodsOrderServiceImpl();
		HttpServletRequest request = ServletActionContext.getRequest();
		goodsOrder = (GoodsOrder) request.getSession().getAttribute("shopCar");

		Member member = new Member();
		member = (Member) request.getSession().getAttribute("loginMember");

		if (member.getMoneyLeft() >= goodsOrder.getOrderAmount()) {
			AddrBookService addrBookService = new AddrBookServiceImpl();
			addrBook = addrBookService.getEntity(addrBook.getBookNo());
			goodsOrder.setOrderTime(new Date());
			goodsOrder.setTimeLeft(goodsOrder.getOrderTime());
			goodsOrder.setMember(member);
			goodsOrder.setOrderRev(addrBook.getRev());
			goodsOrder.setOrderRevAddr(addrBook.getRevAddr());
			goodsOrder.setOrderRevPhone(addrBook.getRevPhone());
			goodsOrder.setOrderStatus("0");
			OrderItemService orderItemService = new OrderItemServiceImpl();
			goodsOrder = goodsOrderService.addEntity(goodsOrder);
			for (OrderItem item : goodsOrder.getOrderItems()) {
				item.setGoodsOrder(goodsOrder);
				orderItemService.addEntity(item);
			}
			member.setMoneyLeft(member.getMoneyLeft()
					- goodsOrder.getOrderAmount());
			/*
			 * member.setMemberRank(member.getMemberRank()+goodsOrder.getOrderAmount
			 * ().intValue()); if(member.getMemberRank()>=1000)
			 * member.setMemberType("1"); else if(member.getMemberRank()>=4000)
			 * member.setMemberType("2"); else if(member.getMemberRank()>=10000)
			 * member.setMemberType("3"); else if(member.getMemberRank()>=40000)
			 * member.setMemberType("4"); else
			 * if(member.getMemberRank()>=100000) member.setMemberType("5");
			 */
			MemberService memberService = new MemberServiceImpl();
			member = memberService.uptEntity(member);
			request.getSession().setAttribute("loginMember", member);
			request.getSession().removeAttribute("shopCar");
		}
		return myMessage();
	}

	public String getList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		if (StringUtils.isNotEmpty(request.getParameter("pageNo")))
			page = new Page(Integer.parseInt(request.getParameter("pageNo")));
		else
			page = new Page();
		GoodsOrderService goodsOrderService = new GoodsOrderServiceImpl();
		page = goodsOrderService.getList(page, orderHelper);
		getTimeLeft();
		return "list";
	}

	public void sendOrder() {
		HttpServletRequest request = ServletActionContext.getRequest();
		GoodsOrderService goodsOrderService = new GoodsOrderServiceImpl();
		goodsOrder = goodsOrderService.getEntity(Integer.parseInt(request
				.getParameter("orderNo")));
		Employee employee = new Employee();
		employee = (Employee) request.getSession().getAttribute("admin");
		goodsOrder.setEmployee(employee);
		goodsOrder.setOrderStatus("1");
		goodsOrder.setTimeLeft(new Date());
		goodsOrder.setLogistics(request.getParameter("logisticsNo"));
		goodsOrderService.uptEntity(goodsOrder);
		PrintWriter out = null;
		try {
			out = ServletActionContext.getResponse().getWriter();
			out.print("success");
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			out.close();
		}

	}

	public void sendBack() {
		HttpServletRequest request = ServletActionContext.getRequest();
		GoodsOrderService goodsOrderService = new GoodsOrderServiceImpl();
		goodsOrder = goodsOrderService.getEntity(Integer.parseInt(request
				.getParameter("orderNo")));
		Employee employee = new Employee();
		employee = (Employee) request.getSession().getAttribute("admin");
		goodsOrder.setEmployee(employee);
		goodsOrder.setOrderStatus("4");
		goodsOrder.setTimeLeft(new Date());
		goodsOrderService.uptEntity(goodsOrder);
		MemberService memberService = new MemberServiceImpl();
		Member member = new Member();
		member = memberService.getEntity(goodsOrder.getMember()
				.getMemberEmail());
		member.setMoneyLeft(member.getMoneyLeft() + goodsOrder.getOrderAmount());

		/*
		 * member.setMemberRank(member.getMemberRank()-goodsOrder.getOrderAmount(
		 * ).intValue()); if(member.getMemberRank()<1000)
		 * member.setMemberType("0"); else if(member.getMemberRank()>=1000)
		 * member.setMemberType("1"); else if(member.getMemberRank()>=4000)
		 * member.setMemberType("2"); else if(member.getMemberRank()>=10000)
		 * member.setMemberType("3"); else if(member.getMemberRank()>=40000)
		 * member.setMemberType("4"); else if(member.getMemberRank()>=100000)
		 * member.setMemberType("5");
		 */

		memberService.uptEntity(member);

	}

	public String makeOrder() {
		HttpServletRequest request = ServletActionContext.getRequest();
		GoodsOrderService goodsOrderService = new GoodsOrderServiceImpl();
		goodsOrder = goodsOrderService.getEntity(Integer.parseInt(request
				.getParameter("orderNo")));
		goodsOrder.setOrderStatus("2");
		goodsOrder.setTimeLeft(new Date());
		goodsOrderService.uptEntity(goodsOrder);
		return myMessage();

	}

	public String estimate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		GoodsOrderService goodsOrderService = new GoodsOrderServiceImpl();
		goodsOrder = goodsOrderService.getEntity(Integer.parseInt(request
				.getParameter("orderNo")));

		List<OrderItem> items = new ArrayList<OrderItem>();
		OrderItem orderItem = new OrderItem();
		orderItem.setGoodsOrder(goodsOrder);
		OrderItemService orderItemService = new OrderItemServiceImpl();
		items = orderItemService.queryEntity(orderItem);

		request.setAttribute("rate", new String[] { "Pretty good", "Meh",
				"Kinda bad", "Sucks big time" });
		goodsOrder.setOrderItems(items);

		return "estimate";

	}

	public void getTimeLeft() {

		GoodsCommentService goodsCommentService = new GoodsCommentServiceImpl();
		GoodsComment comment = new GoodsComment();
		List<GoodsOrder> orders = (List<GoodsOrder>) page.getPageContent();
		HttpServletRequest request = ServletActionContext.getRequest();

		Member member = new Member();
		member = (Member) request.getSession().getAttribute("loginMember");

		for (GoodsOrder gorder : orders) {
			for (OrderItem item : gorder.getOrderItems()) {
				comment.setGoods(item.getGoods());
				comment.setMember(member);
				item.getGoods().setGoodsComments(
						goodsCommentService.queryEntity(comment));
			}
			SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			long lateTime = gorder.getTimeLeft().getTime() + 7 * 24 * 60 * 60
					* 1000;

			long nowtime = new Date().getTime();
			long restTime = lateTime - nowtime;

			long day = restTime / (24 * 3600 * 1000);
			long hour = restTime % (24 * 3600 * 1000) / (3600 * 1000);
			long minute = restTime % (3600 * 1000) / (60 * 1000);
			long second = restTime % (60 * 1000) / 1000;
			String dateString = "0000-00-" + day + " " + hour + ":" + minute
					+ ":" + second;
			Date date = new Date();
			try {
				date = d.parse(dateString);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			gorder.setTimeLeft(date);
			// System.out.println("======================TimeLeft"+gorder.getTimeLeft());
			/*
			 * System.out.println("=======================day"+day);
			 * System.out.println("=======================hour"+hour);
			 * System.out.println("=======================minute"+minute);
			 * System.out.println("=======================second"+second);
			 * System
			 * .out.println("=======================OrderTime"+gorder.getOrderTime
			 * ()); System.out.println("======================TimeLeft"+gorder.
			 * getTimeLeft());
			 * System.out.println("======================nowtime"+date);
			 */
		}
	}

}