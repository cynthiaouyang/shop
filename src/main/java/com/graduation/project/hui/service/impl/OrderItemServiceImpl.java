package com.graduation.project.hui.service.impl;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.graduation.project.hui.DAO.OrderItemDAO;
import com.graduation.project.hui.domain.OrderItem;
import com.graduation.project.hui.service.OrderItemService;
import com.graduation.project.hui.utils.Page;

public class OrderItemServiceImpl implements OrderItemService
{
	private ApplicationContext ac;
	private OrderItemDAO orderItemDAO; 
	public OrderItemServiceImpl()
	{
		ac = new ClassPathXmlApplicationContext("config/spring/spring.xml");
		orderItemDAO = (OrderItemDAO)ac.getBean("orderItemDAO");
	}
	public OrderItem addEntity(OrderItem obj) {
		 
		return orderItemDAO.addEntity(obj);
	}

	public OrderItem delEntity(Integer id) {
		 
		return orderItemDAO.delEntity(id);
	}

	public OrderItem getEntity(Integer id) {
		 
		return orderItemDAO.getEntity(id);
	}

	public OrderItem uptEntity(OrderItem obj) {
		 
		return orderItemDAO.uptEntity(obj);
	}

	public Page getList(Page page, OrderItem helper) {
	//	final String hql=null;		
		page.setTotalRecNum((long)(orderItemDAO.countByCondition(helper)));
		page.setPageContent(orderItemDAO.listEntity(helper, page.getStartIndex(), page.getPageSize()));		
		return page;
	}
	public List<OrderItem> queryEntity(OrderItem obj) {
	//	String[] propertyName=null;
	//	Object[] value=null;
	//	int i = 0;
/*		if (obj.getEmpNo() != null) {
			propertyName[i] = "empNo";
			value[i] = obj.getEmpNo();
			i++;
		}*/
		
		return orderItemDAO.getPackingList(obj);
	}
	public OrderItem mergeEntity(OrderItem obj) {
		// TODO Auto-generated method stub
		return orderItemDAO.mergeEntity(obj);
	}
}