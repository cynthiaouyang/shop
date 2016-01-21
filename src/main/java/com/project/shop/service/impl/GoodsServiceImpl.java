package com.project.shop.service.impl;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.project.shop.DAO.GoodsDAO;
import com.project.shop.domain.Goods;
import com.project.shop.service.GoodsService;
import com.project.shop.utils.Page;

public class GoodsServiceImpl implements GoodsService
{

	private ApplicationContext ac;
	private GoodsDAO goodsDAO; 
	public GoodsServiceImpl()
	{
		ac = new ClassPathXmlApplicationContext("config/spring/spring.xml");
		goodsDAO = (GoodsDAO)ac.getBean("goodsDAO");
	}
	public Goods addEntity(Goods obj) {
		
		return goodsDAO.addEntity(obj);
	}

	public Goods delEntity(Integer id) {
	
		return goodsDAO.delEntity(id);
	}

	public Goods getEntity(Integer id) {
	
		return goodsDAO.getEntity(id);
	}

	public Goods uptEntity(Goods obj) {
	
		return goodsDAO.uptEntity(obj);
	}

	public Page getList(Page page, Goods helper) {
		page.setTotalRecNum((long)(goodsDAO.countByCondition(helper)));
		page.setPageContent(goodsDAO.listEntity(helper, page.getStartIndex(), page.getPageSize()));		
		return page;
	}
	public List<Goods> queryEntity(Goods obj) {		
		return goodsDAO.getPackingList(obj);
	}
	public Goods mergeEntity(Goods obj) {
		return goodsDAO.mergeEntity(obj);
	}
}