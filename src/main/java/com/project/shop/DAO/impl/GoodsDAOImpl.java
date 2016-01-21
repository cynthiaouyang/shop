package com.project.shop.DAO.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.project.shop.DAO.GoodsDAO;
import com.project.shop.domain.Goods;


@Repository("goodsDAO")
public class GoodsDAOImpl extends  GenericDAOImpl<Goods, Integer> implements GoodsDAO
{
	@Resource
	private SessionFactory sessionFactory;
	
	@PostConstruct
	public void setSessionFactory()
	{
		super.setSessionFactory(sessionFactory);
	}
	@Override
	public Query packing(Goods obj) {
		String[] propertyName=new String[7];
		Object[] value=new Object[7];
		String[] ralation=new String[]{"=","="};
		String orderDesc=" order by goodsNo desc";
		int i = 0;
		if(obj!=null){
		if (obj.getCategory()!=null&&obj.getCategory().getCtgNo() != null) {
			propertyName[i] = "category.ctgNo";
			value[i] = obj.getCategory().getCtgNo();
			i++;
		}
		if(obj.getGoodsName()!=null&&!"".equals(obj.getGoodsName())){
			propertyName[i] = "goodsName";
			value[i] = "%"+obj.getGoodsName()+"%";
			ralation[i]=" like ";
			i++;
		}
		if(obj.getGoodsPrice()!=null&&!"".equals(obj.getGoodsPrice())){
			orderDesc=" order by goodsPrice asc";
		}
		if(obj.getGoodsMark()!=null&&!"".equals(obj.getGoodsMark())){
			orderDesc=" order by goodsMark desc";
		}
		if(obj.getSoldAmount()!=null&&!"".equals(obj.getSoldAmount())){
			orderDesc=" order by soldAmount desc";
		}
		if (obj.getGoodsStatus()!=null&&obj.getGoodsStatus() != null) {
			propertyName[i] = "goodsStatus";
			value[i] = obj.getGoodsStatus();
			i++;
		}
		}
		else{
			obj=new Goods();
		}
		return queryEntity(obj.getClass().getSimpleName(),propertyName,value,ralation,orderDesc);
	}
}