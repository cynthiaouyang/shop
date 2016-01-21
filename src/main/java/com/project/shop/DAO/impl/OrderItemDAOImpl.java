package com.project.shop.DAO.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.project.shop.DAO.OrderItemDAO;
import com.project.shop.domain.OrderItem;

@Repository("orderItemDAO")
public class OrderItemDAOImpl extends GenericDAOImpl<OrderItem, Integer> implements OrderItemDAO
{
	@Resource
	private SessionFactory sessionFactory;
	
	@PostConstruct
	public void setSessionFactory()
	{
		super.setSessionFactory(sessionFactory);
	}
	@Override
	public Query packing(OrderItem obj) {
		String[] propertyName=new String[1];
		Object[] value=new Object[1];
		String[] ralation=new String[]{"=","="};
		String orderDesc="";
		int i = 0;
		if (obj.getGoodsOrder()!=null&&obj.getGoodsOrder().getOrderNo() != null) {
			propertyName[i] = "goodsOrder.orderNo";
			value[i] = obj.getGoodsOrder().getOrderNo();
			i++;
		}
		return queryEntity(obj.getClass().getSimpleName(),propertyName,value,ralation,orderDesc);
	}
}