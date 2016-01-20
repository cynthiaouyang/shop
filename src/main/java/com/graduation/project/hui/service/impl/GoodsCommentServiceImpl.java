package com.graduation.project.hui.service.impl;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.graduation.project.hui.DAO.GoodsCommentDAO;
import com.graduation.project.hui.domain.GoodsComment;
import com.graduation.project.hui.service.GoodsCommentService;
import com.graduation.project.hui.utils.Page;



public class GoodsCommentServiceImpl implements GoodsCommentService
 {
	private ApplicationContext ac;
	private GoodsCommentDAO goodsCommentDAO; 
	public GoodsCommentServiceImpl()
	{
		ac = new ClassPathXmlApplicationContext("config/spring/spring.xml");
		goodsCommentDAO = (GoodsCommentDAO)ac.getBean("goodsCommentDAO");
	}

	public GoodsComment addEntity(GoodsComment obj) {
		 
		return goodsCommentDAO.addEntity(obj);
	}

	public GoodsComment delEntity(Integer id) {
		 
		return goodsCommentDAO.delEntity(id);
	}

	public GoodsComment getEntity(Integer id) {
		 
		return goodsCommentDAO.getEntity(id);
	}

	public GoodsComment uptEntity(GoodsComment obj) {
		 
		return goodsCommentDAO.uptEntity(obj);
	}


	public Page getList(Page page, GoodsComment helper) {
	//	final String hql=null;		
		page.setTotalRecNum((long)(goodsCommentDAO.countByCondition(helper)));
		page.setPageContent(goodsCommentDAO.listEntity(helper, page.getStartIndex(), page.getPageSize()));		
		return page;
	}

	public List<GoodsComment> queryEntity(GoodsComment obj) {
	//	String[] propertyName=null;
	//	Object[] value=null;
	//	int i = 0;
/*		if (obj.getEmpNo() != null) {
			propertyName[i] = "empNo";
			value[i] = obj.getEmpNo();
			i++;
		}*/
		
		return goodsCommentDAO.getPackingList(obj);
	}

	public GoodsComment mergeEntity(GoodsComment obj) {
		// TODO Auto-generated method stub
		return goodsCommentDAO.mergeEntity(obj);
	}
	
 }