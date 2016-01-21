package com.project.shop.service.impl;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.project.shop.DAO.GoodsOrderDAO;
import com.project.shop.domain.GoodsOrder;
import com.project.shop.service.GoodsOrderService;
import com.project.shop.utils.Page;




public class GoodsOrderServiceImpl implements  GoodsOrderService 
{

	private ApplicationContext ac;
	private GoodsOrderDAO goodsOrderDAO; 
	public GoodsOrderServiceImpl()
	{
		ac = new ClassPathXmlApplicationContext("config/spring/spring.xml");
		goodsOrderDAO = (GoodsOrderDAO)ac.getBean("goodsOrderDAO");
	}

	public GoodsOrder addEntity(GoodsOrder obj) {
		 
		return goodsOrderDAO.addEntity(obj);
	}

	public GoodsOrder delEntity(Integer id) {
		 
		return goodsOrderDAO.delEntity(id);
	}

	public GoodsOrder getEntity(Integer id) {
		 
		return goodsOrderDAO.getEntity(id);
	}

	public GoodsOrder uptEntity(GoodsOrder obj) {
		 
		return goodsOrderDAO.uptEntity(obj);
	}

/*	public List<GoodsOrder> listEntity() {
		final String hql=null;
		final int begin = 0;
		final int size=0;
		return goodsOrderDAO.listEntity(helper,begin,size);
	}*/

	public Page getList(Page page, GoodsOrder helper) {
	//	final String hql=null;		
		page.setTotalRecNum((long)(goodsOrderDAO.countByCondition(helper)));
		page.setPageContent(goodsOrderDAO.listEntity(helper, page.getStartIndex(), page.getPageSize()));		
		return page;
	}

	public List<GoodsOrder> queryEntity(GoodsOrder obj) {
		//String[] propertyName=null;
		//Object[] value=null;
	//	int i = 0;
/*		if (obj.getEmpNo() != null) {
			propertyName[i] = "empNo";
			value[i] = obj.getEmpNo();
			i++;
		}*/
		
		return goodsOrderDAO.getPackingList(obj);
	}

	public GoodsOrder mergeEntity(GoodsOrder obj) {
		// TODO Auto-generated method stub
		return goodsOrderDAO.mergeEntity(obj);
	}
	}