package com.graduation.project.hui.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.graduation.project.hui.domain.Goods;
import com.graduation.project.hui.domain.GoodsOrder;
import com.graduation.project.hui.domain.OrderItem;
import com.graduation.project.hui.service.GoodsService;
import com.graduation.project.hui.service.OrderItemService;
import com.graduation.project.hui.service.impl.GoodsServiceImpl;
import com.graduation.project.hui.service.impl.OrderItemServiceImpl;

@Controller
@Scope("prototype")
@ParentPackage("orderItem")
public class OrderItemAction {
	private OrderItem orderItem;
	private Goods goods;
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();

	public OrderItem getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}

	public Goods getGoods() {
		return goods;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	@Action(value = "itemAction")
	public void addCar() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Charset", "UTF-8");
		boolean tag = false;
		String html = "";
		List<OrderItem> items = new ArrayList<OrderItem>();
		GoodsOrder order = new GoodsOrder();
		PrintWriter out;
		try {
			out = response.getWriter();
			if (request.getSession().getAttribute("shopCar") != null) {
				if (items.size() == 20) {
				} else {
					order = (GoodsOrder) request.getSession().getAttribute(
							"shopCar");
					items = order.getOrderItems();
					tag = true;
				}
			} else {
				tag = true;
			}
			if (tag) {
				GoodsService goodsService = new GoodsServiceImpl();
				goods = goodsService.getEntity(Integer.parseInt(request
						.getParameter("goodsNo")));
				if (items.size() > 0) {

				} else {
					order.setOrderAmount(0.0);
					order.setOrderQuantity(0);
				}
				OrderItem item = new OrderItem();
				item.setGoods(goods);
				item.setItemColor(java.net.URLDecoder.decode(
						request.getParameter("color"), "UTF-8"));
				item.setItemSize(request.getParameter("size"));
				item.setItemQuantity(Integer.parseInt(request
						.getParameter("num")));
				item.setItemAmount(goods.getGoodsPrice()
						* item.getItemQuantity());
				items.add(item);
				order.setOrderAmount(order.getOrderAmount()
						+ item.getItemAmount());
				order.setOrderQuantity(order.getOrderQuantity()
						+ item.getItemQuantity());
				order.setOrderItems(items);
				request.getSession().setAttribute("shopCar", order);
				html = getCartHtml(order);
			}
			out.print(html);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String getCartHtml(GoodsOrder order) {
		StringBuffer html = new StringBuffer();
		html.append("<ul>");
		if (order.getOrderItems().size() > 0) {
			html.append("<li class='text-uppercase  text-right'><div style='float: left;'>Shopping Bag</div> <div class='text-right'>Subtotal:&nbsp;$"
					+ order.getOrderAmount()
					+ "</div></li><li class='text-uppercase  text-right'><div style='float: left;'><button onclick='shopCart()'>Bag Details</button></div><div class='text-right'><button onclick='checkOut()''>Check out</button></div></li>");
			List<OrderItem> items = order.getOrderItems();
			for (int i = 0; i < items.size(); i++) {
				if (i == 0 || i % 3 == 0) {
					html.append("<ul class='shopcart'>");
				}
				String picName = items.get(i).getGoods().getGoodsPics()
						.split("\\|")[0];
				html.append("<li><div><a href='goods/goodsAction!getEntity.action?goods_no="+items.get(i).getGoods().getGoodsNo()+"'><img src='pics/"
						+ picName
						+ "' width='120px' border=0 id='img"
						+ picName
						+ "'></a></div><div><div class='text-uppercase'><span onclick='removeItem("
						+ i + ")'>Remove</span></div>");
				html.append("<div class='text-uppercase'>"
						+ items.get(i).getGoods().getGoodsShortName()
						+ "</div><div class='text-uppercase'><div><span style='margin-right: 70px;'>"
						+ items.get(i).getItemColor() + "</span><span>"
						+ items.get(i).getItemSize() + "</span>");
				html.append("</div><div><span style='margin-right: 25px;'>$"
						+ items.get(i).getGoods().getGoodsPrice() + "X"
						+ items.get(i).getItemQuantity()
						+ "</span><span style='color: red'>$"
						+ items.get(i).getItemAmount() + "</span></div>");
				html.append("</div><div class='empty-div'>empty</div><div class='empty-div'>empty</div></div></li>");

				if (i == items.size() - 1 || ((i + 1) % 3 == 0) && i != 0) {
					html.append("</ul>");
				}
			}
		} else {
			html.append("<li class='text-uppercase text-right'><div style='float: left;'>Shopping Bag</div><div class=''text-right'>Empty</div></li>");
		}
		html.append("</ul>");
		return html.toString();
	}

	// remove without fresh
	public void remove() {
		PrintWriter out;
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			out = response.getWriter();
			out.print(getCartHtml(removeItem()));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}

	}

	private GoodsOrder removeItem() {
		HttpServletRequest request = ServletActionContext.getRequest();
		GoodsOrder order = new GoodsOrder();
		order = (GoodsOrder) request.getSession().getAttribute("shopCar");
		int index = Integer.parseInt(request.getParameter("index").toString()
				.trim());
		if (order.getOrderItems().size() == 1) {
			order.getOrderItems().remove(0);
			request.getSession().removeAttribute("shopCar");
		} else if (order.getOrderItems().size() > index) {
			order.setOrderAmount(order.getOrderAmount()
					- order.getOrderItems().get(index).getItemAmount());
			order.setOrderQuantity(order.getOrderQuantity()
					- order.getOrderItems().get(index).getItemQuantity());
			order.getOrderItems().remove(index);
			request.getSession().setAttribute("shopCar", order);
		}
		
		return order;
	}

	// remove with fresh
	public String removef() {
		removeItem();
		return showCar();
	}

	public String showCar() {
		return "shopCar";
	}

	public String delEntity() {
		OrderItemService orderItemService = new OrderItemServiceImpl();
		orderItemService.delEntity(orderItem.getItemNo());
		return "success";
	}

	public String getEntity() {
		OrderItemService orderItemService = new OrderItemServiceImpl();
		orderItemService.getEntity(orderItem.getItemNo());
		return "success";
	}

	public String uptEntity() {
		OrderItemService orderItemService = new OrderItemServiceImpl();
		orderItemService.uptEntity(orderItem);
		return "success";
	}
}