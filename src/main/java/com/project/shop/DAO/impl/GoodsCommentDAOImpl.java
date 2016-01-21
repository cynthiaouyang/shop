package com.project.shop.DAO.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.project.shop.DAO.GoodsCommentDAO;
import com.project.shop.domain.GoodsComment;

@Repository("goodsCommentDAO")
public class GoodsCommentDAOImpl extends GenericDAOImpl<GoodsComment, Integer> implements GoodsCommentDAO
{
	@Resource
	private SessionFactory sessionFactory;
	
	@PostConstruct
	public void setSessionFactory()
	{
		super.setSessionFactory(sessionFactory);
	}
	@Override
	public Query packing(GoodsComment obj) {
		String[] propertyName=new String[2];
		Object[] value=new Object[2];
		String[] ralation=new String[]{"=","=","="};
		String orderDesc=" order by commentNo desc";
		int i = 0;
		if (obj.getGoods()!=null&&obj.getGoods().getGoodsNo() != null) {
			propertyName[i] = "goods.goodsNo";
			value[i] = obj.getGoods().getGoodsNo();
			i++;
		}
		if (obj.getMember()!=null&&obj.getMember().getMemberEmail()!= null) {
			propertyName[i] = "member.memberEmail";
			value[i] = obj.getMember().getMemberEmail();
			i++;
		}
		return queryEntity(obj.getClass().getSimpleName(),propertyName,value,ralation,orderDesc);
	}
}