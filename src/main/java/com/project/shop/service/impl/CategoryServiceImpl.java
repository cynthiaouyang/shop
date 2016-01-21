package com.project.shop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;






import com.project.shop.DAO.CategoryDAO;
import com.project.shop.domain.Category;
import com.project.shop.service.CategoryService;
import com.project.shop.utils.Page;

public class CategoryServiceImpl implements CategoryService
{
	private ApplicationContext ac;
	private CategoryDAO categoryDAO; 
	public CategoryServiceImpl()
	{
		ac = new ClassPathXmlApplicationContext("config/spring/spring.xml");
		categoryDAO = (CategoryDAO)ac.getBean("categoryDAO");
	}

	public Category addEntity(Category obj) {

		return categoryDAO.addEntity(obj);
	}

	public Category delEntity(String id) {
		// TODO Auto-generated method stub
		return categoryDAO.delEntity(id);
	}

	public Category getEntity(String id) {
		// TODO Auto-generated method stub
		return categoryDAO.getEntity(id);
	}

	public Category uptEntity(Category obj) {
		// TODO Auto-generated method stub
		return categoryDAO.uptEntity(obj);
	}

	public Page getList(Page page, Category helper) {
	//	final String hql="from Category c  order by c.ctgNo";		
		page.setTotalRecNum((long)(categoryDAO.countByCondition(helper)));
		page.setPageContent(categoryDAO.listEntity(helper, page.getStartIndex(), page.getPageSize()));		
		return page;
	}

	public List<Category> queryEntity(Category obj) {
		List<Category> list=new ArrayList<Category>();
		list=categoryDAO.getPackingList(obj);
		return list;
	}

	public Category mergeEntity(Category obj) {
		// TODO Auto-generated method stub
		return categoryDAO.mergeEntity(obj);
	}


	
}