package com.project.shop.DAO.impl;


import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.project.shop.DAO.CategoryDAO;
import com.project.shop.domain.Category;


@Repository("categoryDAO")
public class CategoryDAOImpl extends  GenericDAOImpl<Category, String> implements CategoryDAO
{
	@Resource
	private SessionFactory sessionFactory;
	
	@PostConstruct
	public void setSessionFactory()
	{
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public Query packing(Category obj) {
		String[] propertyName=new String[1];
		Object[] value=new Object[1];
		String[] ralation=new String[]{"=","="};
		String orderDesc="";
		int i = 0;
		if (obj.getCategory()!=null&&obj.getCategory().getCtgNo() != null) {
			propertyName[i] = "category.ctgNo";
			value[i] = obj.getCategory().getCtgNo();
			i++;
		}
		return queryEntity(obj.getClass().getSimpleName(),propertyName,value,ralation,orderDesc);
	}
	
}